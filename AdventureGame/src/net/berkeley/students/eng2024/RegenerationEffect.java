package net.berkeley.students.eng2024;

public class RegenerationEffect implements Effect{

    private final String name = "regeneration";
    private final boolean good = true;
    private final String applicationString = "gave you regeneration";
    private int duration;
    private double potency;
    private Living host;

    public RegenerationEffect(Living host, int duration, double potency) {
        this.host = host;
        this.duration = duration;
        this.potency = potency;
    }
    public RegenerationEffect(int duration, double potency) {
        this.duration = duration;
        this.potency = potency;
    }

    public boolean affect() {
        host.heal(potency);
        if (host instanceof Player) {
            AdventureGame.notify("notice","You are regenerating health. You healed " + AdventureGame.numf(potency) + " hitpoints.");
        }
        else {
            AdventureGame.notify("notice",((Creature)host).name() + "is regeneration. Its hitpoints have been increased by " + AdventureGame.numf(potency) + ".");
        }
        duration--;
        if (duration <= 0) {
            AdventureGame.notify("notice","The regeneration effect wears off.");
            host.removeEffect(this);
            return false;
        }
        return true;
    }

    public int duration() { return duration; }
    public String name() { return name; }
    public String applicationString() { return applicationString;}
    public boolean good() { return good; }
    public Living host() { return host; }
    public void setHost(Living host) { this.host = host;}
}
