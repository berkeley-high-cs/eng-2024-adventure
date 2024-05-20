package net.berkeley.students.eng2024;

import java.util.ArrayList;
import java.util.List;

public class FoodItem implements UsableItem{
    
    private String name;
    private List<String> abbreviations;
    private String description;
    private double healthChange;
    private List<Effect> effects;
    //"ate", "drank", "wolfed down"
    private String usageString;
    private String usageFlavorText;

    private FoodItem(String name, String description, double healthChange, List<Effect> effects, String usageString, String usageFlavorText, List<String> abbreviations) {
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
    public List<String> abbreviations() {
        return abbreviations;
    }
    public String usageFlavorText() {
        return usageFlavorText;
    }

    public void use(Player p) {
        String s = String.format("You %s the %s, %s %s health. ",usageString,name,healthChange > 0 ? "and healed" : "and lost", AdventureGame.numf(healthChange));
        for (Effect effect : effects) {
            s += String.format("The %s %s.", name, effect.applicationString());
            p.addEffect(effect);
        }
        p.changeHitpoints(healthChange);
        
        AdventureGame.notify("notice",s);
        if (usageFlavorText.length() > 0) {
            AdventureGame.notify("info",usageFlavorText);
        }
    }

    public static class FoodItemBuilder {

        private String name;
        private List<String> abbreviations;
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
            abbreviations = new ArrayList<>();
            usageString = "consumed";
            usageFlavorText = "";
        }
        public FoodItemBuilder addAbbreviations(String... abbreviations) {
            for (String a : abbreviations) {
                this.abbreviations.add(a);
            }
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
