package net.berkeley.students.eng2024;

import java.util.List;
import java.util.ArrayList;

public class Passage {
    private String name;
    private List<String> additionalNames;
    private String movementDescription;
    private Room r1;
    private Room r2;
    private boolean isSeeThrough;

    public Passage(String name, String movementDescription, Room r1, Room r2, boolean isSeeThrough) {
        this.name = name;
        this.additionalNames = List.of();
        this.movementDescription = movementDescription;
        this.r1 = r1;
        this.r2 = r2;
        this.isSeeThrough = isSeeThrough;
        r1.addPassage(this);
        r2.addPassage(this);
    }
    public Passage(String name, List<String> additionalNames, String movementDescription, Room r1, Room r2, boolean isSeeThrough) {
        this.name = name;
        this.additionalNames = additionalNames;
        this.movementDescription = movementDescription;
        this.r1 = r1;
        this.r2 = r2;
        this.isSeeThrough = isSeeThrough;
        r1.addPassage(this);
        r2.addPassage(this);
    }
    public Passage(String name, String movementDescription, String name1, String name2, boolean isSeeThrough) {
        this.name = name;
        this.additionalNames = List.of();
        this.movementDescription = movementDescription;
        Room r1 = AdventureGame.map.findRoom(name1);
        Room r2 = AdventureGame.map.findRoom(name2);
        this.r1 = r1;
        this.r2 = r2;
        this.isSeeThrough = isSeeThrough;
        r1.addPassage(this);
        r2.addPassage(this);
    }
    public Passage(String name, List<String> additionalNames, String movementDescription, String name1, String name2, boolean isSeeThrough) {
        this.name = name;
        this.additionalNames = additionalNames;
        this.movementDescription = movementDescription;
        Room r1 = AdventureGame.map.findRoom(name1);
        Room r2 = AdventureGame.map.findRoom(name2);
        this.r1 = r1;
        this.r2 = r2;
        this.isSeeThrough = isSeeThrough;
        r1.addPassage(this);
        r2.addPassage(this);
    }

    public boolean matches(String str) {
        for (String s : getAllNames()) {
            if (str.contains(s)) { return true;}
        }
        return false;
    }

    public Room notPlayerRoom() {
        return r1 == AdventureGame.player.getRoom() ? r2 : r1;
    }
    public Room notRoom(Room r) {
        return r1 == r ? r2 : r1;
    }
    public boolean connects(Room r) {
        return r1 == r || r2 == r;
    }

    public String getMovementDescription() {
        return movementDescription;
    }

    public Room getRoom1() {
        return r1;
    }

    public Room getRoom2() {
        return r2;
    }

    public boolean isSeeThrough() {
        return isSeeThrough;
    }

    public String getName() { return name; }
    public List<String> getAllNames() { 
        List<String> names = new ArrayList<>(additionalNames);
        names.add(name);
        return names;
    }

    public String toString(Room r) {
        String s = "";
        s += "\n>>There is a " + name
                    + (isSeeThrough
                            ? ", and you can see " + notRoom(r).getPassageDescription() + " beyond it."
                            : ".");
        return s;
    }
    public String toString() {
        return name;
    }
    

}
