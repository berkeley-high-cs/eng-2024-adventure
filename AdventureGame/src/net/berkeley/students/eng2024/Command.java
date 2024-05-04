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
    private static final Command[] BASECOMMANDS = new Command[] {
            new Command("attack", KEYWORDS_ATTACK),
            new Command("pickup", KEYWORDS_PICKUP),
            new Command("drop", KEYWORDS_DROP),
            new Command("move", KEYWORDS_MOVE),
            new Command("return", KEYWORDS_RETURN),
            new Command("inspect", KEYWORDS_INSPECT)
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
        switch (name) {
            case "attack":
                ActionAttack(action);
            case "pickup":
                ActionPickup(action);
            case "drop":
                ActionDrop(action);
            case "move":
                ActionMove(action);
            case "return":
                ActionReturn(action);
            case "inspect":
                ActionInspect(action);
            default:
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

    // if no additional text is provided, attacks random available enemy
    // otherwise user may specify a weapon or enemy
    private void ActionAttack(String action) {

    }

    // if only one item is in the room, automatically picks it up, otherwise
    // requires name
    private void ActionPickup(String action) {

    }

    // if no additional text is provided, drops the most recently picked up item
    // otherwise user may specify an item in their inventory
    private void ActionDrop(String action) {

    }

    // if only one passage is available in the room THAT IS NOT THE WAY THE PLAYER
    // CAME, player goes there
    // otherwise user may specify which passage they want to take
    private void ActionMove(String action) {
        Player player = AdventureGame.player;
        int keyIndex = keywordIndex(keywords, action);
        if (keyIndex == -1) { return; }
        Room pRoom = player.getRoom();
        List<String> validPassages = pRoom.getPassages().stream().map(p -> p.name()).toList();
        String subAction = action.substring(keyIndex);
        for (String passage : validPassages) {
            if (subAction.contains(passage)) {
                player.moveToRoom(pRoom.getConnectingRoom(passage));
            }
        }
        if (pRoom.getPassages().size() == 2) {
            player.moveToRoom(player.lastRoom());
            for (Passage passage : pRoom.getPassages()) {
                if (player.lastRoom() != passage.r1() && player.lastRoom() != passage.r2()) {
                    player.moveToRoom(passage.notPlayerRoom());
                }
            }
        }
        //behavior for when there is no obvious room to go to
    }

    // no additional arguments, just a shorthand for going back the way the player
    // came
    private void ActionReturn(String action) {

    }

    // inspect literally anything
    private void ActionInspect(String action) {

    }
    public String toString(){
        return name;
    }
}
