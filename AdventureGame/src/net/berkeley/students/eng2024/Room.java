package net.berkeley.students.eng2024;

import java.util.ArrayList;
import java.util.List;

public class Room {

    // codeName should never be shown to the player at all
    // passageDescription is used for any things that connect to this room that the
    // player can see through, e.g. a broken window (so like, it would say There is
    // a broken window, and
    // you can see [passageDescription] on the other side.)
    private ArrayList<Entity> entities;
    private String description;
    private String codeName;
    private ArrayList<Passage> passages;
    private String passageDescription;

    public Room(String codeName, String passageDescription, String description, ArrayList<Passage> p,
            ArrayList<Entity> items) {
        this.codeName = codeName;
        this.description = description;
        this.passageDescription = passageDescription;
        passages = new ArrayList<>();
        this.entities = items;
        passages = p;
    }

    public Room(String codeName, String passageDescription, String description, ArrayList<Entity> items) {
        this.codeName = codeName;
        this.description = description;
        this.passageDescription = passageDescription;
        passages = new ArrayList<>();
        this.entities = items;
        passages = new ArrayList<>();
    }

    // describe the room itself and all the items and everythin
    public String describe() {
        String s = AdventureGame.format("longinfo",description);
        for (Entity entity : entities) {
            s += AdventureGame.format("notice", "There is a " + entity.name() + ".");
        }
        for (Passage p : passages) {
            if (AdventureGame.player.lastRoom() == p.notPlayerRoom()) {
                continue;
            }
            s += p.toString(this);
        }
        return s;
    }

    public void addPassage(Passage passage) {
        passages.add(passage);
    }

    public List<Passage> getPassages() {
        return passages;
    }

    public Room getConnectingRoom(String passageName) {
        Passage passage = passages.stream().filter(p -> p.getName().toLowerCase().equals(passageName.toLowerCase()))
                .findFirst().get();
        return passage.getRoom1() == this ? passage.getRoom2() : passage.getRoom1();
    }

    public String getDescription() {
        return description;
    }

    public String getPassageDescription() {
        return passageDescription;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public void removeEntity(Entity item) {
        entities.remove(item);
    }

    public void addEntity(Entity item) {
        entities.add(item);
    }

    public String getName() {
        return codeName;
    }

}