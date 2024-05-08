package net.berkeley.students.eng2024;

public class Passage {
    private String name;
    private String description;
    private Room r1;
    private Room r2;
    private boolean accessible;

    public Passage(String name, String description, Room r1, Room r2, boolean accessible) {
        this.name = name;
        this.description = description;
        this.r1 = r1;
        this.r2 = r2;
        this.accessible = accessible;
        r1.addPassage(this);
        r2.addPassage(this);
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

    public String getDescription() {
        return description;
    }

    public Room getRoom1() {
        return r1;
    }

    public Room getRoom2() {
        return r2;
    }

    public boolean isAccessible() {
        return accessible;
    }

    public String getName() { return name; }

}
