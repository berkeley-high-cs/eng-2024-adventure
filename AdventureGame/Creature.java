package src.main.java.org.adventure;

public class Creature implements IEntity {

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

    public double getHitpoints() { return this.hitpoints; }
    public boolean isHostile() { return this.isHostile; }
    public String getName() { return this.name; }
    public String getDescription() { return this.description; }
    public void setHitpoints(double hitpoints) { this.hitpoints = hitpoints; }

}
