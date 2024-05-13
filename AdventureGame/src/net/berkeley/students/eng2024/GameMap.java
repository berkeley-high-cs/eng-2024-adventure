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
        new Room("otherRoom", "the other room", "other room description", new ArrayList<>()),
        new Room("otherOtherRoom", "the third room", "third room description", new ArrayList<>()),
        new Room("monsterRoom", "the monster room", "A monster lurks at the center of the room", new ArrayList<>(List.of(Weapon.BRONZE_DAGGER, Creature.GOBLIN_CHILD)))
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
            if (room.getName().equals(codeName)) { 
                return room;
            }
        }
        return null;
    }

    public GameMap() {
    }
    //these two get methods are also used as the mutator methods. DO NOT edit these arrays if you don't want them edited in this location.
    public Room[] getRooms() {
        return rooms;
    }

    public Passage[] getPassages() {
        return passages;
    }

}
