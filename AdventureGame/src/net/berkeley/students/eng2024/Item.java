package net.berkeley.students.eng2024;

public interface Item extends Entity{

    default void drop(Player p) {
        p.dropItem(this);
    }

    default void pickup(Player p) {
        p.pickupItem(this);
    }

}
