package net.berkeley.students.eng2024;

public interface IItem {

    public void use(Player p);

    default void drop(Player p) {
    }

    default void pickup(Player p) {
    }

    public String description();

    public String name();

}
