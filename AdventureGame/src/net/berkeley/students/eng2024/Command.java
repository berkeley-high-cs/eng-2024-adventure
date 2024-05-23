package net.berkeley.students.eng2024;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
                player.getRoom().removeEntity((Entity) item);
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
            List<Entity> entities = player.getRoom().getEntities();
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
            for (Item item : player.getItems()) {
                if (action.contains(item.name())) {
                    itemToDrop = item;
                    break;
                }
            }
            if (itemToDrop == null && player.getItems().size() > 0) {
                itemToDrop = player.getItems().getLast();
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
            Room pRoom = player.getRoom();
            Passage targetPassage = null;
            for (Passage passage : pRoom.getPassages()) {
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
            if (targetRoom == player.getRoom()) {
                AdventureGame.notify("warning", "There is no room to go back to.");
                return true;
            }
            Passage targetPassage = player.getRoom().getPassages().stream().filter(p -> p.connects(targetRoom))
                    .findFirst().get();
            AdventureGame.notify("notice", "You go back through the " + targetPassage.getName() + ".");
            player.takePassage(targetPassage);
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
            AdventureGame.notify("notice", "You have " + player.getHitpoints() + " health.");
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
            Player player = AdventureGame.player;

            for (String s : roomKeywords) {
                if (action.contains(s)) {
                    System.out.println(player.getRoom().describe());
                    return true;
                }
            }

            List<String> itemNames = player.getItems().stream().map(x -> x.name()).toList();
            for (String s : itemNames) {
                if (action.contains(s)) {
                    Item item = player.getItems().stream().filter(x -> x.name().equals(s)).findFirst().get();
                    AdventureGame.notify("info", item.name());
                    AdventureGame.notify("info", item.description());
                    return true;
                }
            }

            // inspecting something in the room the player is in
            Stream<Entity> entities = player.getRoom().getEntities().stream();
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

        public static record ConverseCommand(Player player) implements Command{
            ArrayList<npc> npcs = player.getRoom().getNpcs();
            ArrayList<String> npcsNames = new ArrayList<String>(npcs.stream().map(npc -> npc.name()).collect());

            public static final String[] keywords = {"say"};

            public static final String[] getDiologOptions = {"dioOptions"};

            public final String[] talkables = npcsNames;

            

            public boolean doCommand(String action){


                for (String opts : getDiologOptions) {
                    if(action.contains(opts)){
                        int i = Command.super.keywordIndex(getDiologOptions, action);

                        action.substring(i);
                        for (npc npc : npcs) {
                            if(npc.name().equals(action)){
                                
                            }
                        }
                        
                        return true;
                    }
                }

                int i = Command.super.keywordIndex(keywords, action);
                if (i == -1) {
                    return false;
                }
                action = action.substring(i);
         
                
                for (npc npc : npcs) {
                    if(npc.name().equals(action)){
                        Conversation con = new Conversation(npc);
                    }
                }
            }
        }
        
        

        public String toString() {
            return "inspect";
        }
    };

}
