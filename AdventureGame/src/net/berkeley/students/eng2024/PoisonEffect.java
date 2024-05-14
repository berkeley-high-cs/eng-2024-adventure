package net.berkeley.students.eng2024;

public class PoisonEffect implements Effect{

    private final String name = "poison";
    private final boolean good = false;
    private final String applicationString = "poisoned";
    private int duration;
    private double potency;
    private Living host;

    public PoisonEffect(Living host, int duration, double potency) {
        this.host = host;
        this.duration = duration;
        this.potency = potency;
    }
    public PoisonEffect(int duration, double potency) {
        this.duration = duration;
        this.potency = potency;
    }

    public boolean affect() {
        host.damage(potency);
        if (host instanceof Player) {
            AdventureGame.notify("notice","You are poisoned. Your hitpoints have been reduced by " + potency + ".");
        }
        else {
            AdventureGame.notify("notice",((Creature)host).name() + "is poisoned. Its hitpoints has been reduced by " + potency + ".");
        }
        duration--;
        if (duration <= 0) {
            AdventureGame.notify("notice","The poison wears off.");
            host.removeEffect(this);
            return false;
        }
        return true;
    }

    public int getDuration() { return duration; }
    public String getName() { return name; }
    public String getApplicationString() { return applicationString;}
    public boolean isGood() { return good; }
    public Living getHost() { return host; }
    public void setHost(Living host) { this.host = host;}
}
