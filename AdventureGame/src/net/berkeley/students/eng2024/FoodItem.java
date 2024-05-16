package net.berkeley.students.eng2024;

import java.util.ArrayList;
import java.util.List;

public class FoodItem implements UsableItem{
    
    private String name;
    private String[] abbreviations;
    private String description;
    private double healthChange;
    private List<Effect> effects;
    //"ate", "drank", "wolfed down"
    private String usageString;
    private String usageFlavorText;

    private FoodItem(String name, String description, double healthChange, List<Effect> effects, String usageString, String usageFlavorText, String[] abbreviations) {
        this.name = name;
        this.description = description;
        this.healthChange = healthChange;
        this.effects = effects;
        this.usageString = usageString;
        this.usageFlavorText = usageFlavorText;
        this.abbreviations = abbreviations;
    }
    public String name() {
        return name;
    }
    public String description() {
        return description;
    }
    public double healthChange() {
        return healthChange;
    }
    public List<Effect> effects() {
        return effects;
    }
    public String usageString() {
        return usageString;
    }
    public String[] abbreviations() {
        return abbreviations;
    }
    public String usageFlavorText() {
        return usageFlavorText;
    }

    public void use(Player p) {
        String s = String.format("You %s the %s, %s %f health.",usageString,name,healthChange > 0 ? "and healed" : "and lost", healthChange);
        for (Effect effect : effects) {
            s += String.format("The %s %s you.", name, effect.applicationString());
            p.addEffect(effect);
        }
        p.changeHitpoints(healthChange);
        AdventureGame.notify("notice",s);
    }

    public static class FoodItemBuilder {

        private String name;
        private String[] abbreviations;
        private String description;
        private double healthChange;
        private List<Effect> effects;
        //"ate", "drank", "wolfed down"
        private String usageString;
        private String usageFlavorText;

        public FoodItemBuilder(String name, String description, double healthChange) {
            this.name = name;
            this.description = description;
            this.healthChange = healthChange;
            effects = new ArrayList<>();
            abbreviations = new String[] {};
            usageString = "consumed";
            usageFlavorText = "";
        }
        public FoodItemBuilder addAbbreviations(String... abbreviations) {
            this.abbreviations = abbreviations;
            return this;
        }
        public FoodItemBuilder addEffect(Effect effect) {
            effects.add(effect);
            return this;
        }
        public FoodItemBuilder addUsageString(String usageString) {
            this.usageString = usageString;
            return this;
        }
        public FoodItemBuilder addUsageFlavorText(String usageFlavorText) {
            this.usageFlavorText = usageFlavorText;
            return this;
        }
        public FoodItem toFoodItem() {
            return new FoodItem(name,description,healthChange,effects,usageString,usageFlavorText,abbreviations);
        }


    }

}
