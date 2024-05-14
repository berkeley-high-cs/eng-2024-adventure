package net.berkeley.students.eng2024;

import java.util.List;

public class FoodItem implements UsableItem{
    
    private String name;
    private String[] abbreviations;
    private String description;
    private double healthChange;
    private List<Effect> effects;
    //"ate", "drank", "wolfed down"
    private String consumptionString;

    public FoodItem(String name, String description, double healthChange, List<Effect> effects, String consumptionString) {
        this.name = name;
        this.description = description;
        this.healthChange = healthChange;
        this.effects = effects;
        this.consumptionString = consumptionString;
    }
    public String name() {
        return name;
    }
    public String description() {
        return description;
    }
    public double getHealthChange() {
        return healthChange;
    }
    public List<Effect> getEffects() {
        return effects;
    }
    public String getConsumptionString() {
        return consumptionString;
    }

    public void use(Player p) {
        String s = String.format("You %s the %s, %s %f health.",consumptionString,name,healthChange > 0 ? "and healed" : "and lost", healthChange);
        for (Effect effect : effects) {
            s += String.format("The %s %s you.", name, effect.getApplicationString());
            p.addEffect(effect);
        }
        p.changeHitpoints(healthChange);
        AdventureGame.notify("notice",s);
    }

}
