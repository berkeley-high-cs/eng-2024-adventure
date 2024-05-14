package net.berkeley.students.eng2024;

public interface UsableItem extends Item {
    public void use(Player p);

    public String[] abbreviations();
}
