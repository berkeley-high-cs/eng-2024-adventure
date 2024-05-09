package net.berkeley.students.eng2024;

public class PoisonEffect implements Effect{

    private final String name = "poison";
    private final boolean good = false;
    private int duration;
    private double potency;

    public PoisonEffect(int duration, double potency) {
        this.duration = duration;
        this.potency = potency;
    }

    public void affect(Living target) {
        target.damage(potency);
        if (target instanceof Player) {
            AdventureGame.notify("notice","You are poisoned. Your hitpoints have been reduced by " + potency + ".");
        }
        else {
            AdventureGame.notify("notice",((Creature)target).name() + "is poisoned. Its hitpoints has been reduced by " + potency + ".");
        }
        duration--;
        if (duration <= 0) {
            AdventureGame.notify("notice","The poison wears off.");
            target.removeEffect(this);
        }
    }

    public int getDuration() { return duration; }
    public String getName() { return name; }
    public boolean isGood() { return good; }
}
