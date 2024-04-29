package src.main.java.org.adventure;

public interface IItem {
    
    public void use(/*Player p*/);

    default void drop(/*Player p*/){}

    default void pickup(/*Player p*/){}

    static class POTION_OF_HEALING implements IItem{
        public void use(Player p){
            p.changeHealth(5);
        }
    }
    public String description(){
        return "A potion that heals"
    }

}
