package net.berkeley.students.eng2024;

import java.util.Arrays;
import java.util.List;

public record Command(String name, String[] keywords) {

    private static final String[] KEYWORDS_ATTACK = new String[] {
            "attack", "fight", "stab", "punch", "beat up"
    };
    private static final String[] KEYWORDS_PICKUP = new String[] {
            "pick up", "pickup", "take"
    };
    private static final String[] KEYWORDS_DROP = new String[] {
            "drop", "leave behind"
    };
    private static final String[] KEYWORDS_MOVE = new String[] {
            "go", "move"
    };
    private static final String[] KEYWORDS_RETURN = new String[] {
            "go back", "leave", "return"
    };
    private static final String[] KEYWORDS_INSPECT = new String[] {
            "check out", "inspect", "look at", "view"
    };
    private static final String[] KEYWORDS_CURRENTROOM = new String[] {
            "room", "area", "surroundings"
    };
    private static final String[] KEYWORDS_STATUS = new String[] {
            "self", "status"
    };
    //implement a sort based on precedence
    private static final Command[] BASECOMMANDS = new Command[] {
            new Command("attack", KEYWORDS_ATTACK),
            new Command("pickup", KEYWORDS_PICKUP),
            new Command("drop", KEYWORDS_DROP),
            new Command("move", KEYWORDS_MOVE),
            new Command("return", KEYWORDS_RETURN),
            new Command("inspect", KEYWORDS_INSPECT),
            new Command("status", new String[] { "status" })
    };

    // sometimes the player could have the opportunity to do other things, like
    // unlocking a door or trading with a merchant. this will check the current room
    // and entities within that room
    // including the player's inventory for any other things they could do, in
    // addition to the base 5
    public static List<Command> AvailableCommands() {
        List<Command> available = Arrays.asList(BASECOMMANDS);
        return available;
    }

    public boolean ActionIsThisCommand(String action) {
        for (String s : keywords) {
            if (action.contains(s)) {
                return true;
            }
        }
        return false;
    }

    // if an extra command is added (e.g. unlocking a door) make sure to add a case
    // for it here!! does not necessarily need to be a method of this Record
    public void TakeAction(String action) {
        int keyIndex = keywordIndex(keywords, action);
        // if no move keyword was inputted, return
        if (keyIndex == -1) {
            return;
        }
        String subAction = action.substring(keyIndex);
        switch (name) {
            case "attack":
                ActionAttack(subAction);
                break;
            case "pickup":
                ActionPickup(subAction);
                break;
            case "drop":
                ActionDrop(subAction);
                break;
            case "move":
                ActionMove(subAction);
                break;
            case "return":
                ActionReturn(subAction);
                break;
            case "inspect":
                ActionInspect(subAction);
                break;
            case "status":
                ActionStatus();
                break;
        }
    }

    private static final int keywordIndex(String[] keywords, String str) {
        for (String s : keywords) {
            int i = str.indexOf(s);
            if (i > -1) {
                return i + s.length();
            }
        }
        return -1;
    }

    // if no additional text is provided, attacks random available enemy with most
    // recent weapon
    // otherwise user may specify a weapon or enemy
    private void ActionAttack(String action) {

    }

    // if only one item is in the room, automatically picks it up, otherwise
    // requires name
    private void ActionPickup(String action) {
        Player player = AdventureGame.player;
        Item targetItem = null;
        List<Entity> entities = player.getRoom().getEntities();
        for (Entity entity : entities) {
            if (entity instanceof Item && action.contains(entity.name())) {
                targetItem = (Item)entity;
            }
        }
        
        if (targetItem == null && entities.size() == 1 && entities.getFirst() instanceof Item) {
            targetItem = (Item)entities.getFirst();
        }
        if (targetItem != null) {
            player.getRoom().removeEntity((Entity)targetItem);
            targetItem.pickup(player);
            AdventureGame.notify("notice", "You picked up the " + targetItem.name() + ".");
            return;
        }
        AdventureGame.notify("warning", "Please specify an item to be picked up.");
    }

    // if no additional text is provided, drops the most recently picked up item
    // otherwise user may specify an item in their inventory
    private void ActionDrop(String action) {
        Player player = AdventureGame.player;
        Item itemToDrop = null;
        for (Item item : player.getItems()) {
            if (action.contains(item.name())) {
                itemToDrop = item;
                break;
            }
        }
        if (itemToDrop == null && player.getItems().size() > 0) {
            itemToDrop = player.getItems().getLast();
        }
        if (itemToDrop != null) {
            itemToDrop.drop(player);
            AdventureGame.notify("notice", "You dropped the " + itemToDrop.name() + ".");
            return;
        }
        // code for behavior when there are no items the player has
        AdventureGame.notify("warning", "You don't have any items to drop.");
    }

    // if only one passage is available in the room THAT IS NOT THE WAY THE PLAYER
    // CAME, player goes there
    // otherwise user may specify which passage they want to take
    private void ActionMove(String action) {
        Player player = AdventureGame.player;
        // getting the different passages of the current room and checking to see if
        // message contains them
        Room pRoom = player.getRoom();
        List<String> validPassages = pRoom.getPassages().stream().map(p -> p.getName()).toList();
        Passage targetPassage = null;
        for (String passage : validPassages) {
            if (action.contains(passage)) {
                //targetRoom = pRoom.getConnectingRoom(passage);
                targetPassage = pRoom.getPassages().stream().filter(p -> p.getName().equals(passage)).findFirst().get();
                break;
            }
        }

        // if not, goes to the passage that the player didnt enter through
        if (targetPassage == null && pRoom.getPassages().size() == 2) {
            player.moveToRoom(player.lastRoom());
            for (Passage passage : pRoom.getPassages()) {
                if (player.lastRoom() != passage.getRoom1() && player.lastRoom() != passage.getRoom2()) {
                    targetPassage = passage;
                    break;
                }
            }
        }
        if (targetPassage != null) {
            AdventureGame.notify("notice", "You make your way through the " + targetPassage.getName() + ".");
            player.takePassage(targetPassage);
            return;
        }
        // if there is no obvious room to go to
        AdventureGame.notify("warning", "Please specify where you'd like to move to.");
    }

    // no additional arguments, just a shorthand for going back the way the player
    // came
    private void ActionReturn(String action) {
        Player player = AdventureGame.player;
        Room targetRoom = player.lastRoom();
        if (targetRoom == player.getRoom()) {
            AdventureGame.notify("warning", "There is no room to go back to.");
            return;
        }
        Passage targetPassage = player.getRoom().getPassages().stream().filter(p -> p.connects(targetRoom)).findFirst().get();
        AdventureGame.notify("notice", "You make your way through the " + targetPassage.getName() + ".");
        player.takePassage(targetPassage);
        
    }

    // inspect literally anything - creatures in room, player status, the room
    // itself, any item the player has
    private void ActionInspect(String action) {
        Player player = AdventureGame.player;
        for (String s : KEYWORDS_STATUS) {
            if (action.contains(s)) {
                ActionStatus();

                return;
            }
        }

        for (String s : KEYWORDS_CURRENTROOM) {
            if (action.contains(s)) {
                AdventureGame.notify("info", player.getRoom().getDescription());

                return;
            }
        }

        List<String> itemNames = player.getItems().stream().map(x -> x.name()).toList();
        for (String s : itemNames) {
            if (action.contains(s)) {
                Item item = player.getItems().stream().filter(x -> x.name().equals(s)).findFirst().get();

                AdventureGame.notify("info", item.name());
                AdventureGame.notify("info", item.description());
                return;
            }
        }

        // implement behavior for inspecting a creature

        // behavior if nothing to inspect was specified
        AdventureGame.notify("warning", "Please specify what you'd like to inspect.");
    }

    // checks players current status
    private void ActionStatus() {
        AdventureGame.notify("info", "You have " + AdventureGame.player.getHitpoints() + " health.");
    }

    public String toString() {
        return name;
    }
}
