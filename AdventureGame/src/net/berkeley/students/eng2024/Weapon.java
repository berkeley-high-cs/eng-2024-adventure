package net.berkeley.students.eng2024;

//defult weapon class, can be customized for weapons that act differently
public class Weapon implements Item {

    private int damage;
    private String name;
    private String description;

    public Weapon(int dam, String n, String desc) {
        damage = dam;
        name = n;
        description = desc;
    }

    public String name() {
        return this.name();
    }

    public String description() {
        return this.description;
    }

    public int damage() {
        return damage;
    }

    //waiting on implementation of attack command
    public void use(Player p) {   
        
    }


    public void drop(Player p) {
        p.dropItem(this);
    }

    public void pickup(Player p) {
        p.pickupItem(this);
    }

}