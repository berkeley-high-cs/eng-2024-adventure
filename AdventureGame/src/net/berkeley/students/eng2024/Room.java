package net.berkeley.students.eng2024;

import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class Room {

    // codeName should never be shown to the player at all
    // passageDescription is used for any things that connect to this room that the
    // player can see through, e.g. a broken window (so like, it would say There is
    // a broken window, and
    // you can see [passageDescription] on the other side.)
    private List<Entity> entities;
    private String description;
    private String codeName;
    private List<Passage> passages;
    private String passageDescription;

    public Room(String codeName, String passageDescription, String description, List<Passage> p,
            List<Entity> items) {
        this.codeName = codeName;
        this.description = description;
        this.passageDescription = passageDescription;
        passages = new ArrayList<>();
        this.entities = items;
        passages = p;
    }

    public Room(String codeName, String passageDescription, String description, List<Entity> items) {
        this.codeName = codeName;
        this.description = description;
        this.passageDescription = passageDescription;
        passages = new ArrayList<>();
        this.entities = items;
        passages = new ArrayList<>();
    }

    // describe the room itself and all the items and everythin
    public String describe() {
        String s = AdventureGame.format("info",description);
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

    public List<Passage> passages() {
        return passages;
    }

    public Room getConnectingRoom(String passageName) {
        Passage passage = passages.stream().filter(p -> p.getName().toLowerCase().equals(passageName.toLowerCase()))
                .findFirst().get();
        return passage.getRoom1() == this ? passage.getRoom2() : passage.getRoom1();
    }

    public String description() {
        return description;
    }

    public String passageDescription() {
        return passageDescription;
    }

    public List<Entity> entities() {
        return entities;
    }

    public void removeEntity(Entity item) {
        entities.remove(item);
    }

    public void addEntity(Entity item) {
        entities.add(item);
    }

    public String codeName() {
        return codeName;
    }

}