package net.berkeley.students.eng2024;

public interface Effect {

    public String getName();
    public void affect();
    public boolean isGood();
    public int getDuration();
    public Living getHost();


}
