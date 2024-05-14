package net.berkeley.students.eng2024;

public class ProceduralGeneration {
    private int numRooms;
    public ProceduralGeneration(int numRooms){
        this.numRooms = numRooms;
    }

    public Room[] generateRooms(){
        Room[] rooms = new Room[numRooms];
        for(int i = 0; i < numRooms; i++){
            rooms[i] = new Room("Room " + i, "Room number " + i, "The " + i + "th room.", null);
        }
        
        return rooms;
    }
    public Passage[] generatePassages(Room[] rooms){
        Passage[] passages = new Passage[numRooms - 1];
        for(int i = 0; i < numRooms - 1; i++){
            passages[i] = new Passage("door", "You open door and walk through", rooms[i], rooms[i+1], false);
        }
        return passages;
    }
}
