package net.berkeley.students.eng2024;

public interface Effect {

    public String getName();
    //IMPORTANT: Returns true if the effect is still active after the call, returns false if it has expired (required for activating each effect without a crash)
    public boolean affect();
    public boolean isGood();
    public int getDuration();
    public Living getHost();


}
