package net.berkeley.students.eng2024;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface Command {

    public String toString();

    public boolean doCommand(String action);

    default int keywordIndex(String[] keywords, String str) {
        for (String s : keywords) {
            int i = str.indexOf(s);
            if (i > -1) {
                return i + s.length();
            }
        }
        return -1;
    }
    default String InverseKeywordIndex(List<String> keywords, String str) {
        for (String s : keywords) {
            int i = str.indexOf(s);
            if (i > -1) {
                return s;
            }
        }
        return "";
    }

    public static record AttackCommand(Player player) implements Command {
        private static final String[] keywords = new String[] {
                "attack", "fight", "stab", "punch", "beat up"
        };

        // if no additional text is provided, attacks random available enemy with most
        // recent weapon
        // otherwise user may specify a weapon or enemy
        public boolean doCommand(String action) {
            int i = Command.super.keywordIndex(keywords, action);
            if (i == -1) {
                return false;
            }
            action = action.substring(i);
            Optional<Weapon> weapon = player.activeWeapon(action);
            if(weapon.isEmpty()){
                AdventureGame.notify("warning", "There is nothing to attack with");
                return false;
            }
            Optional<Creature> target = player.room().containsCreature(action);
            if(target.isEmpty()){
                AdventureGame.notify("warning", "A creature with the specified name cannot be found");
                return false;
            }
            target.get().damageByWeapon(weapon.get());
            AdventureGame.notify("notice", target.get().status());
            return true;
        }

        public String toString() {
            return "attack";
        }
    };

    public static record PickupCommand(Player player) implements Command {
        private static final String[] keywords = new String[] {
                "pick up", "pickup", "take"
        };

        private boolean pickupItem(Item item) {
            if (item != null) {
                player.room().removeEntity((Entity) item);
                item.pickup(player);
                AdventureGame.notify("notice", "You picked up the " + item.name() + ".");
                return true;
            }
            return false;
        }

        public boolean doCommand(String action) {
            int i = Command.super.keywordIndex(keywords, action);
            if (i == -1) {
                return false;
            }
            action = action.substring(i);
            Item targetItem = null;
            List<Entity> entities = player.room().entities();
            for (Entity entity : entities) {
                if (entity instanceof Item && action.contains(entity.name())) {
                    targetItem = (Item) entity;
                }
            }

            if (targetItem == null && entities.size() == 1 && entities.getFirst() instanceof Item) {
                targetItem = (Item) entities.getFirst();
            }
            if (pickupItem(targetItem)) {
                return true;
            }
            AdventureGame.notify("warning", "Please specify an item to be picked up.");
            return true;
        }

        public String toString() {
            return "pickup";
        }

    };

    public static record DropCommand(Player player) implements Command {
        private static final String[] keywords = new String[] {
                "drop", "leave behind", "get rid of"
        };

        private boolean dropItem(Item item) {
            if (item != null) {
                item.drop(player);
                AdventureGame.notify("notice", "You dropped the " + item.name() + ".");
                return true;
            }
            return false;
        }

        public boolean doCommand(String action) {
            int i = Command.super.keywordIndex(keywords, action);
            if (i == -1) {
                return false;
            }
            action = action.substring(i);
            Item itemToDrop = null;
            for (Item item : player.items()) {
                if (action.contains(item.name())) {
                    itemToDrop = item;
                    break;
                }
            }
            if (itemToDrop == null && player.items().size() > 0) {
                itemToDrop = player.items().getLast();
            }
            if (dropItem(itemToDrop)) {
                return true;
            }
            // code for behavior when there are no items the player has
            AdventureGame.notify("warning", "You don't have any items to drop.");
            return true;
        }

        public String toString() {
            return "drop";
        }
    };

    public static record MoveCommand(Player player) implements Command {
        private static final String[] keywords = new String[] {
                "go", "move", ""
        };

        private boolean moveThrough(Passage passage) {
            if (passage != null) {
                AdventureGame.notify("notice", "You " + passage.getMovementDescription());
                player.takePassage(passage);
                return true;
            }
            return false;
        }

        public boolean doCommand(String action) {
            int i = Command.super.keywordIndex(keywords, action);
            // "" is a keyword so i is always positive
            action = action.substring(i);
            // getting the different passages of the current room and checking to see if
            // message contains them
            Room pRoom = player.room();
            Passage targetPassage = null;
            for (Passage passage : pRoom.passages()) {
                if (passage.matches(action)) {
                    targetPassage = passage;
                    break;
                }
            }
            if (moveThrough(targetPassage)) {
                return true;
            }
            // if there is no obvious room to go to
            // so i is only 0 if there was no specific move keyword, in which case we want
            // to tell the user to use a keyword
            if (i == 0) {
                return false;
            }
            AdventureGame.notify("warning", "Please specify where you'd like to move to.");
            return true;
        }

        public String toString() {
            return "move";
        }
    };

    public static record ReturnCommand(Player player) implements Command {
        private static final String[] keywords = new String[] {
                "back", "leave", "return"
        };

        public boolean doCommand(String action) {
            int i = Command.super.keywordIndex(keywords, action);
            if (i == -1) {
                return false;
            }
            action = action.substring(i);
            Room targetRoom = player.lastRoom();
            if (targetRoom == player.room()) {
                AdventureGame.notify("warning", "There is no room to go back to.");
                return true;
            }
            Passage targetPassage = player.room().passages().stream().filter(p -> p.connects(targetRoom))
                    .findFirst().get();
            AdventureGame.notify("notice", "You go back through the " + targetPassage.getName() + ".");
            player.goBackThroughPassage(targetPassage);
            return true;
        }

        public String toString() {
            return "go back";
        }
    };

    public static record InspectCommand(Player player) implements Command {
        private static final String[] keywords = new String[] {
                "check out", "inspect", "look at", "view", "check"
        };
        private static final String[] statusKeywords = new String[] {
                "status"
        };
        private static final String[] roomKeywords = new String[] {
                "room", "surroundings", "area"
        };

        private void playerStatus() {
            AdventureGame.notify("notice", "You have " + player.hitpoints() + " health.");
        }

        public boolean doCommand(String action) {
            for (String s : statusKeywords) {
                if (action.contains(s)) {
                    playerStatus();
                    return true;
                }
            }
            int i = Command.super.keywordIndex(keywords, action);
            if (i == -1) {
                return false;
            }
            action = action.substring(i);

            for (String s : roomKeywords) {
                if (action.contains(s)) {
                    System.out.println(player.room().describe());
                    return true;
                }
            }

            List<String> itemNames = player.items().stream().map(x -> x.name()).toList();
            for (String s : itemNames) {
                if (action.contains(s)) {
                    Item item = player.items().stream().filter(x -> x.name().equals(s)).findFirst().get();
                    AdventureGame.notify("info", item.name());
                    AdventureGame.notify("info", item.description());
                    return true;
                }
            }

            // inspecting something in the room the player is in
            Stream<Entity> entities = player.room().entities().stream();
            List<String> entityNames = entities.map(x -> x.name()).toList();
            for (String s : entityNames) {
                if (action.contains(s)) {
                    Entity entity = entities.filter(x -> x.name().equals(s)).findFirst().get();
                    AdventureGame.notify("info", entity.name());
                    AdventureGame.notify("info", entity.description());
                    return true;
                }
            }

            // behavior if nothing to inspect was specified
            AdventureGame.notify("warning", "Please specify what you'd like to inspect.");
            return true;
        }

        public String toString() {
            return "inspect";
        }
    };

    public static record InventoryCommand(Player player) implements Command {
        private static final String[] keywords = new String[] {
                "inventory", "i have", "inv"
        };

        public boolean doCommand(String action) {
            int i = Command.super.keywordIndex(keywords, action);
            if (i == -1) {
                return false;
            }
            action = action.substring(i);
            List<Item> items = player.items();
            if (items.size() == 0) {
                AdventureGame.notify("notice","You don't have any items in your inventory.");
                return true;
            }
            String s = "You've got a ";
            for (int j = 0; j < items.size(); j++) {
                s += items.get(j).name() + (j < items.size() - 2 ? ", a " : (j == items.size() - 2 ? ", and a " : "." ));
            }     
            AdventureGame.notify("info",s);
            return true;
        }

        public String toString() {
            return "inventory";
        }
    };

    public static record UseCommand(Player player) implements Command {

        private static final String[] keywords = new String[] {
            "use"
        };

        private List<String> playerItems(Stream<UsableItem> usables) {
            ArrayList<String> itemNames = new ArrayList<>();
            for (UsableItem i : usables.toList()) {
                for (String s : i.allNames()) {
                    itemNames.add(s);
                }
            }
            return itemNames;
        }
        private Stream<UsableItem> playerUsables() {
            return player.items().stream().filter(item -> item instanceof UsableItem).map(item -> (UsableItem)item);
        }

        public boolean doCommand(String action) {
            ArrayList<String> itemNames = new ArrayList<>(playerItems(playerUsables()));
            String itemName = Command.super.InverseKeywordIndex(itemNames, action);
            if (itemName.equals("")) {
                if (Command.super.keywordIndex(keywords,action) != -1) {
                    AdventureGame.notify("warning","Please specify what you wish to use.");
                } 
                return false;
            }

            UsableItem item = playerUsables().filter(i -> i.allNames().contains(itemName)).findFirst().get();

            item.use(player);

            
            return true;
        }

        public String toString() {
            return "inventory";
        }
    };


}
