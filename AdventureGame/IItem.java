package src.main.java.org.adventure;

public interface IItem {
    
    public void use(Player p);

    default void drop(Player p){}

    default void pickup(Player p){}

}
