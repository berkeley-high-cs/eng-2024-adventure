package net.berkeley.students.eng2024;

public interface Effect {

    public String getName();
    public void affect(Living target);
    public boolean isGood();
    public int getDuration();


}
