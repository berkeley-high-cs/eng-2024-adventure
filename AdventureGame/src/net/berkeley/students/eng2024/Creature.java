package net.berkeley.students.eng2024;

import java.util.ArrayList;
import java.util.List;

public class Creature implements Entity, Living {

    private double hitpoints;
    private boolean isHostile;
    private String name;
    private String description;
    private ArrayList<Effect> effects;

    public Creature(String name, String description, double hitpoints, boolean isHostile) {
        this.hitpoints = hitpoints;
        this.isHostile = isHostile;
        this.name = name;
        this.description = description;
        effects = new ArrayList<>();
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

    public void setHitpoints(double n) {
        this.hitpoints = n;
    }

    public List<Effect> getEffects() {
        return effects;
    }
    public void addEffect(Effect e) {
        effects.add(e);
    }
    public boolean removeEffect(Effect e) {
        return effects.remove(e);
    }
    //Creatures
    public static Creature GOBLIN_CHILD = new Creature("goblin child","They make look harmless, but growing up in the streets of Goblintopia, they're ruthless.", 5,true);

}
