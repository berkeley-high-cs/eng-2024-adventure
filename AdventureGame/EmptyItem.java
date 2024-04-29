public class EmptyItem implements IItem{
    public void use(/*Player p*/);

    default void drop(/*Player p*/){}

    default void pickup(/*Player p*/){}
}