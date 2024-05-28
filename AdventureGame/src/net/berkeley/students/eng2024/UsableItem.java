package net.berkeley.students.eng2024;

import java.util.ArrayList;
import java.util.List;

public interface UsableItem extends Item {
    public void use(Player p);

    public List<String> abbreviations();

    public String usageString();

    public default String usageFlavorText() {
        return "";
    }

    public default List<String> allNames() {
        ArrayList<String> names = new ArrayList<>(abbreviations());  
        names.add(name());
        return names;
    }
}
