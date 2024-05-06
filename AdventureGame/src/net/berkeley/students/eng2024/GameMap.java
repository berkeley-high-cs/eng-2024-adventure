package net.berkeley.students.eng2024;

public class GameMap {
    // public Room(String codeName, String passageDescription, String description){

    private Room[] rooms = { new Room("starter", "two passages to the left", "very empty") };

    public GameMap() {
    }

    public Room[] getRooms() {
        return rooms;
    }

}