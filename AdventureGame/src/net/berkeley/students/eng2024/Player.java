package net.berkeley.students.eng2024;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public Optional<Weapon> activeWeapon(){
        return items().stream().filter((i) -> i instanceof Weapon).map(i -> (Weapon) i).findFirst();
    }
    public Optional<Weapon> activeWeapon(String weaponName){
        //!! there is a cleaner way to do this but im pressed on time and it's not very important
        Optional<Weapon> weapon = items().stream().filter((i) -> i instanceof Weapon).map(i -> (Weapon) i).filter(w -> weaponName.contains(w.name())).findFirst();
        if(weapon.isPresent()) return weapon;
        return activeWeapon();
    }

}
