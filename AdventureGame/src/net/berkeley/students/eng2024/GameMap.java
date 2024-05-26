package net.berkeley.students.eng2024;

import java.util.ArrayList;
import java.util.List;

public class GameMap {
    //reference for rooms:
    //codeName: never shown to the player, quick small description of the room for identification through strings in the code
    //passageDescription: short description of the room through a passage. for example, in an adjacent room it could say "There is a broken window, and you "
    //can see [passageDescription] beyond it."
    //description: the actual long text that describes the room itself, without all the passages or items or creatures

    private Room[] rooms = { 
        new Room("starterRoom", "the starter room", "starter room description line 1\nstarter room description line 2\nstarter room description line 3", new ArrayList<>()),
        new Room("emptyRoom", "encounter with the princess and an attack from bandits", "other room description", new ArrayList<>()),
        new Room("floweryMeadow", "a blank room with nothing but a field", "third room description", new ArrayList<>()),
        new Room("banditCamp", "the bandit room. Seems scary", "A group of bandits lurks at the center of the room", new ArrayList<>(List.of(Weapon.BRONZE_DAGGER, Creature.GOBLIN_CHILD))),
        new Room("nurseRoom", "a medic bay with a nurse in the middle of it", "nurse room objects", new ArrayList<>()),
        new Room("floweryMeadow", "meadow with pure roses", "meadow objects", new ArrayList<>()),
        new Room("boxerRoom", "boxing ring with a strong opponent in the middle of it", "boxing gloves maybe", new ArrayList<>()),
        new Room("banditRoom", "group of bandits", "bandit loot", new ArrayList<>()),
        new Room("banditRoomTwo", "second bandit room", "bandit loot", new ArrayList<>()),
        new Room("itemRoom", "treasure room", "treasure", new ArrayList<>()),
        new Room("bucketRoom", "ominous looking bucket is in the middle", "bucket", new ArrayList<>()),
        new Room("banditRoomThree", "bandits", "bandit loot", new ArrayList<>()),
        new Room("mailRoom", "mailroom with a single letter", "letter", new ArrayList<>()),
        new Room("lakeRoom", "large lake", "water", new ArrayList<>()),
        new Room("meadow", "large meadow", "flowers", new ArrayList<>()),
        new Room("itemRoomTwo", "empty room with loot", "loot", new ArrayList<>()),
        new Room("revolutionCamp", "small camp of tough looking horses", "revolutionary items", new ArrayList<>()),
        new Room("emptyFountain", "dry fountain with no water", "fountain dust", new ArrayList<>()),
        new Room("meadowRoom", "meadow with fields of daisies", "flowers", new ArrayList<>()),
        new Room("guardRoom", "tough looking guards", "guard loot", new ArrayList<>()),
        new Room("bossRoom", "a huge fighter", "loot from the fighter", new ArrayList<>()),
        new Room("questItemRoom", "a tree stump with something in the middle", "quest item", new ArrayList<>()),
    };

    //reference for passages:
    //name: is used in gameplay, as a short (1 or 2 words) description for the passage
    //additional names: if your passage name can be shortened, this optional parameter is a list of all the abbreviated ways to say it (e.g. the player will type out 'window' instead of 'broken window')
    //movementDescription: tells the player how they went through the passage (preceded by 'You ')
    //next 2 arguments are the 2 rooms it connects
    //last argument defines whether or not the passage is see-through, and if so then it will also give a brief description of those rooms
    private Passage[] passages = {
        new Passage("broken window", List.of("window"), "crawl through the window, making sure not to hurt yourself on the shards of glass.", rooms[0], rooms[1], true),
        new Passage("trapdoor", "approach the trapdoor.\nIt appears to lack any sort of window, but you shrug that slightly worrying fact off and open it anyways.", rooms[2], rooms[0], false),
        new Passage("door", "hesitantly open the door, ignoring the large creaking sound and continue through it.", rooms[1], rooms[2], false),
        new Passage("foor", "as you approach the door, the wails from the other side grow louder",rooms[2],rooms[3],false)
    };

    public Room findRoom(String codeName) {
        for (Room room : rooms) {
            if (room.codeName().equals(codeName)) { 
                return room;
            }
        }
        return null;
    }

    public GameMap() {
    }
    //these two get methods are also used as the mutator methods. DO NOT edit these arrays if you don't want them edited in this location.
    public Room[] rooms() {
        return rooms;
    }

    public Passage[] passages() {
        return passages;
    }

}
