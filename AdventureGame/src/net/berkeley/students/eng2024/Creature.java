package net.berkeley.students.eng2024;

public class Creature implements Entity {

    private double hitpoints;
    private boolean isHostile;
    private String name;
    private String description;

    public Creature(String name, String description, double hitpoints, boolean isHostile) {
        this.hitpoints = hitpoints;
        this.isHostile = isHostile;
        this.name = name;
        this.description = description;
    }

    public double getHitpoints() {
        return this.hitpoints;
    }

    public boolean isHostile() {
        return this.isHostile;
    }

    public String name() {
        return this.name;
    }

    public String description() {
        return this.description;
    }

    public void setHitpoints(double hitpoints) {
        this.hitpoints = hitpoints;
    }
    //Creatures
    public static Creature GOBLIN_CHILD = new Creature("goblin child","They make look harmless, but growing up in the streets of Goblintopia, they're ruthless.", 5,true);

}
