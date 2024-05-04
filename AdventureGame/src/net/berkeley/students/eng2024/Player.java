package net.berkeley.students.eng2024;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private Room currentRoom;
    private double hitpoints;
    public final double maxHitpoints = 100;
    private AdventureGame adventureGame;

    //please keep this sorted (end of set is most recent)
    private ArrayList<Room> visitedRooms;
    

    public Player(Room startRoom, AdventureGame adventureGame) {
        this.currentRoom = startRoom;
        this.adventureGame = adventureGame;
        this.hitpoints = maxHitpoints;
        visitedRooms = new ArrayList<>();
        visitedRooms.add(currentRoom);
    }

    public Room getRoom() { return this.currentRoom; }
    public double getHitpoints() { return this.hitpoints; }
    public void moveToRoom(Room room) { 
        this.currentRoom = room; 
        if (visitedRooms.contains(room)) {
            visitedRooms.remove(room);
        }
        visitedRooms.add(room);
    }
    public List<Room> getVisitedRooms() { return visitedRooms; }
    public Room lastRoom() { 
        return visitedRooms.size() == 1 ? currentRoom : visitedRooms.get(visitedRooms.size()-2);
    }
    public void setHitpoints(double hitpoints) { this.hitpoints = hitpoints; }

}
