package net.berkeley.students.eng2024;

import java.util.ArrayList;

public class GameMap {
    //reference for rooms:
    //codeName: never shown to the player, quick small description of the room for identification through strings in the code
    //passageDescription: short description of the room through a passage. for example, in an adjacent room it could say "There is a broken window, and you "
    //can see [passageDescription] beyond it."
    //description: the actual long text that describes the room itself, without all the passages or items or creatures

    private Room[] rooms = { 
        new Room("starterRoom", "the starter room", "starter room description line 1\nstarter room description line 2\nstarter room description line 3", new ArrayList<>()),
        new Room("otherRoom", "the other room", "very empty 2", new ArrayList<>())
    };
    private Passage[] passages = {
        new Passage("window", "a window", rooms[0], rooms[1], true)
    };

    public GameMap() {
    }

    public Room[] getRooms() {
        return rooms;
    }

}