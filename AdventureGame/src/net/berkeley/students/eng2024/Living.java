package net.berkeley.students.eng2024;

import java.util.List;

public interface Living {

    public double getHitpoints();
    public default void damage(double n) {
        setHitpoints(getHitpoints() - n);
    }
    public default void heal(double n) {
        setHitpoints(getHitpoints() + n);
    }
    public void setHitpoints(double n);

    public List<Effect> getEffects();
    public void addEffect(Effect e);
    public boolean removeEffect(Effect e);
    public default void cleanseEffects() {
        for (Effect e : getEffects()) {
            removeEffect(e);
        }
    }
    default void activateEffects() {
        for (Effect e : getEffects()) {
            e.affect(this);
        }
    }

}
