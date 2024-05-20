package net.berkeley.students.eng2024;

import java.util.ArrayList;
import java.util.List;

public class Creature implements Entity, Living {

    private final double maxHitpoints;
    private double hitpoints;
    private boolean isHostile;
    private String name;
    private String description;
    private ArrayList<Effect> effects;

    public Creature(String name, String description, double hitpoints, boolean isHostile) {
        this.hitpoints = hitpoints;
        this.maxHitpoints = hitpoints;
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
    public void setHostile(boolean b){
        isHostile = true;
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
    public String status(){
        String statusMessage = name() + " is in ";
        if(hitpoints < 0) return name() + " is dead";
        else if(hitpoints > maxHitpoints*0.75) statusMessage+="good condition";
        else if(hitpoints > maxHitpoints*0.5) statusMessage+="moderate condition";
        else if(hitpoints > maxHitpoints*0.25) statusMessage+="poor condition";
        else{statusMessage+="miserable condition";}
        return statusMessage;

    }

    public void damageByWeapon(Weapon weapon){
        damage(weapon.damage());
        if(hitpoints < 0){
            handleDeath();
        }
        setHostile(true);
    }
    //TODO UNIMPLEMENTED (should it be part of living interface?)
    void handleDeath(){

    }

    //Creatures
    public static Creature GOBLIN_CHILD = new Creature("goblin child","They make look harmless, but growing up in the streets of Goblintopia, they're ruthless.", 5,true);

}
