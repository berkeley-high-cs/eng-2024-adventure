package net.berkeley.students.eng2024;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.*;

public class Room {

    //codeName should never be shown to the player at all
    //passageDescription is used for any things that connect to this room that the player can see through, e.g. a broken window (so like, it would say There is a broken window, and
    // you can see [passageDescription] on the other side.)
    private IItem[][] grid;
    private String roomDescription;
    private String codeName;
    private List<Passage> passages;
    private String passageDescription;

    public Room(String codeName, String passageDescription, String description, List<Passage> p){
        this.codeName = codeName;
        this.roomDescription = description;
        this.passageDescription = passageDescription;
        passages = new ArrayList<>();
        grid = new IItem[3][3];
        passages = p;
    }

    public void addPassage(Passage passage) {
        passages.add(passage);
    }
    public List<Passage> getPassages() { 
        return passages;
    }
    public Room getConnectingRoom(String passageName) { 
        Passage passage = passages.stream().filter(p -> p.name().toLowerCase().equals(passageName.toLowerCase())).findFirst().get();
        return passage.r1() == this ? passage.r2() : passage.r1();
    }


    public String getDescription() {
        return roomDescription;
    }

    public String getPassageDescription() { return passageDescription; }

    public IItem[][] getLayout() {
        return grid;
    }

    public void changeLayout(IItem thing, int x, int y) {
        grid[x][y] = thing;
    }

    public void changeThing(IItem thing, IItem newThing) {
        for (int s = 0; s < 3; s++) {
            for (int t = 0; s < 3; t++) {
                if (grid[s][t] == thing) {
                    grid[s][t] = newThing;
                }
            }
        }
    }

    public void changeThing(IItem thing) {
        for (int s = 0; s < 3; s++) {
            for (int t = 0; s < 3; t++) {
                if (grid[s][t] == thing) {
                    grid[s][t] = null;
                }
            }
        }
    }

    public String getName() { return codeName; }

}