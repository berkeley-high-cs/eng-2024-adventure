package net.berkeley.students.eng2024;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private Room currentRoom;
    private double hitpoints;
    public final double maxHitpoints = 100;
    List<Item> items;

    // please keep this sorted (end of set is most recent)
    private List<Room> visitedRooms;

    // please keep this sorted (end of set is most recent)
    private List<Passage> passagesTaken;

    public Player(Room startRoom) {
        this.hitpoints = maxHitpoints;
        this.currentRoom = startRoom;
        items = new ArrayList<>();
        visitedRooms = new ArrayList<>();
        passagesTaken = new ArrayList<>();
        
    }

    public Room getRoom() {
        return this.currentRoom;
    }

    public double getHitpoints() {
        return this.hitpoints;
    }

    public void takePassage(Passage passage) {
        if (passagesTaken.contains(passage)) {
            passagesTaken.remove(passage);
        }
        passagesTaken.add(passage);
        moveToRoom(passage.notPlayerRoom());
    }

    public void moveToRoom(Room room) {
        this.currentRoom = room;
        if (visitedRooms.contains(room)) {
            visitedRooms.remove(room);
        }
        visitedRooms.add(room);

        AdventureGame.notify("borderless", room.describe());
    }

    public List<Room> getVisitedRooms() {
        return visitedRooms;
    }

    public Room lastRoom() {
        return visitedRooms.size() == 1 ? currentRoom : visitedRooms.get(visitedRooms.size() - 2);
    }

    public void setHitpoints(double hitpoints) {
        this.hitpoints = hitpoints;
    }

    public List<Item> getItems() {
        return items;
    }

    public void dropItem(Item item) {
        // implement item going into the room the player is in
        items.remove(item);
    }

    public void pickupItem(Item item) {
        items.add(item);
    }

}
