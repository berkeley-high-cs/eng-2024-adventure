package net.berkeley.students.eng2024;

public interface Effect {

    public String getName();
    //Used for when the effect is applied for better readability.
    //Should follow this grammar: "[noun] [application string] you/[creature name]"
    //ex. The rotten apple poisoned you.                   (application string: poisoned)
    //ex 2. The sandwich applied regeneration to you.      (application string: applied regeneration to)
    public String getApplicationString();
    //IMPORTANT: Returns true if the effect is still active after the call, returns false if it has expired (required for activating each effect without a crash)
    public boolean affect();
    public boolean isGood();
    public int getDuration();
    public Living getHost();
    public void setHost(Living host);


}
