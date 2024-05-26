package net.berkeley.students.eng2024;

import java.util.List;

public interface Living {

    public double hitpoints();
    public default void damage(double n) {
        setHitpoints(hitpoints() - n);
    }
    public default void heal(double n) {
        setHitpoints(hitpoints() + n);
    }
    public default void changeHitpoints(double n) {
        setHitpoints(hitpoints() + n);
    }
    public void setHitpoints(double n);

    public List<Effect> effects();
    public void addEffect(Effect e);
    public boolean removeEffect(Effect e);
    public default void cleanseEffects() {
        for (Effect e : effects()) {
            removeEffect(e);
        }
    }
    default void activateEffects() {
        int i = 0;
        while (i < effects().size()) {
            if (effects().get(i).affect()) {
                i++;
            }
        }
    }

}
