package net.berkeley.students.eng2024;


public record Passage(String name, String description, Room r1, Room r2, boolean accessible) {

    public Room notPlayerRoom() {
        return r1 == AdventureGame.player.getRoom() ? r2 : r1;
    }

}