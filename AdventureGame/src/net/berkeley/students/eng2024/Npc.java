package net.berkeley.students.eng2024;

import java.util.ArrayList;

class Npc extends Creature{
    
    private ArrayList<Item> inventory; 
    private Lines lines;
    public Npc(String name, String description, double hitpoints, boolean isHostile, ArrayList<Item> inventory, Lines lines) {
        super(name, description, hitpoints, isHostile);
        this.inventory = inventory;
        this.lines = lines;
    }

    public ArrayList<Item> getInventory(){
        return this.inventory;
    }

    public void removeItem(Item item){
        if(inventory.indexOf(item)!=-1){
            inventory.remove(inventory.indexOf(item));
        }
    }

    public void addItem(Item item){
        inventory.add(item);
    }

    public String diolog(String ask){
        return lines.returnResponse(ask);
    }

    public String getUiqueTriggers(){//gets Triggers for unique npc lines
        return lines.getTriggers();
    }




    //Npc HORSE_MAN = new Npc("horse man", "Normal harmless modest horse man. ", 20, false, new ArrayList<Item>(new weapon(3, "butter knife", "Spreads butter")));

}