package net.berkeley.students.eng2024;

public interface Item extends Entity{

    public void use(Player p);

    default void drop(Player p) {
    }

    default void pickup(Player p) {
    }


}
