package net.berkeley.students.eng2024;

import java.util.ArrayList;
import java.util.Arrays;

public interface UsableItem extends Item {
    public void use(Player p);

    public String[] abbreviations();

    public String usageString();

    public default String usageFlavorText() {
        return "";
    }

    public default String[] allNames() {
        ArrayList<String> names = new ArrayList<>();
        if (abbreviations().length > 0) {
            names.addAll(Arrays.asList(abbreviations()));
        }
        
        names.add(name());
        return (String[]) (names.toArray());
    }
}
