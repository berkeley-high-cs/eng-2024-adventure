package net.berkeley.students.eng2024;

public interface ICreature extends IEntity {

    public String description();

    public String name();

    
    public String attack(Player p);

    //Will take weapon type once added
    public String getAttacked();

    public String getHealth();

    public void changeHealth(int n);

    //This section is only necessary if we want to have monsters have items  
    public IItem getItem(); 

    public boolean hasItem();

    public void dropItem(); 


    
}
