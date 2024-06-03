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

    public double hitpoints() {
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
    public boolean isIntelligent(){
        return false;
    }
    //Creatures
    public static Creature GOBLIN_CHILD = new Creature("goblin child","They make look harmless, but growing up in the streets of Goblintopia, they're ruthless.", 5,true);
    public static Creature Nurse = new Creature("Filipp the nurse", "A long haired, dangerously good looking Nurse by the name of Filipp", 1000000000, false);
    public static Creature Boxer = new Creature("a steadfast gentlehorse", "worn down from years of battle", 100, false);
    public static Creature Guards = new Creature("loyalist bastards with the monarchy crest on their chests", "they look mean, scary, tough, anti-revolutionary, and slightly horseaphobic", 20, true);
    public static Creature Princess = new Creature("the royal princes","monarchist without a single bone of care or love for her subjects", 100, true);
}
