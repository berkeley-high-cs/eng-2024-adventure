package net.berkeley.students.eng2024;

import java.util.ArrayList;
import java.util.List;

public class ProceduralGeneration {
    private int numRooms;

    public ProceduralGeneration(int numRooms) {
        this.numRooms = numRooms;
    }

    public Room[] generateRooms() {
        Room[] rooms = new Room[numRooms];
        for (int i = 0; i < numRooms; i++) {
            rooms[i] = new Room(
                "Room " + i, 
                "Room number " + i, 
                "The " + i + "th room.", 
                new ArrayList<>()
            );
        }
        return rooms;
    }

    public Passage[] generatePassages(Room[] rooms) {
        Passage[] passages = new Passage[numRooms];
        String[] passageNames = {"broken window", "trapdoor", "door", "other"}; 
        for (int i = 0; i < numRooms - 1; i++) {
            passages[i] = new Passage(
                passageNames[i % passageNames.length], // Iterates through all of the passage names
                "You open " + passageNames[i % passageNames.length] + " and walk through", 
                rooms[i], 
                rooms[i + 1], 
                false
            );
        }

        return passages;
    }
}
