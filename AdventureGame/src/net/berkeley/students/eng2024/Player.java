package net.berkeley.students.eng2024;

import java.util.ArrayList;
import java.util.List;

public class Player implements Living {

    private Room currentRoom;
    private double hitpoints;
    public final double maxHitpoints = 100;
    private ArrayList<Item> items;
    private ArrayList<Effect> effects;
    private static final int MAX_ROOM_MEMORY = 10; //maximum amount of rooms and passages you can consecutively go back through

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
        effects = new ArrayList<>();
        pickupItem(new FoodItem.FoodItemBuilder("rotten apple", "Something you really shouldn't eat!", -10)
        .addEffect(new PoisonEffect(3,10))
        .addUsageFlavorText("Why the fuck did I eat that shit?")
        .toFoodItem());
        pickupItem(new FoodItem.FoodItemBuilder("golden apple", "Something you really should eat!", 20)
        .addEffect(new RegenerationEffect(3,10))
        .addUsageFlavorText("Delicious...")
        .addAbbreviations("gold apple")
        .toFoodItem());
    }

    public Room room() {
        return this.currentRoom;
    }

    public double hitpoints() {
        return this.hitpoints;
    }

    public void takePassage(Passage passage) {
        passagesTaken.add(passage);
        if (passagesTaken.size() > MAX_ROOM_MEMORY) { passagesTaken.removeFirst(); }
        moveToRoom(passage.notPlayerRoom());
    }

    public void moveToRoom(Room room) {
        this.currentRoom = room;
        visitedRooms.add(room);
        if (visitedRooms.size() > MAX_ROOM_MEMORY) { visitedRooms.removeFirst(); }

        AdventureGame.notify("borderless", room.describe());
        activateEffects();
    }

    public void goBackThroughPassage(Passage passage) {
        passagesTaken.removeLast();
        visitedRooms.removeLast();
        this.currentRoom = passage.notPlayerRoom();

        AdventureGame.notify("borderless", currentRoom.describe());
        activateEffects();
    }

    public List<Room> visitedRooms() {
        return visitedRooms;
    }

    public Room lastRoom() {
        return visitedRooms.size() == 1 ? currentRoom : visitedRooms.get(visitedRooms.size() - 2);
    }

    public void setHitpoints(double n) {
        this.hitpoints = n;
    }

    public List<Item> items() {
        return items;
    }

    public void dropItem(Item item) {
        // implement item going into the room the player is in
        items.remove(item);
    }

    public void pickupItem(Item item) {
        items.add(item);
    }

    public List<Effect> effects() {
        return effects;
    }
    public void addEffect(Effect e) {
        effects.add(e);
        e.setHost(this);
    }
    public boolean removeEffect(Effect e) {
        return effects.remove(e);
    }

}
