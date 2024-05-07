package net.berkeley.students.eng2024;

import java.util.ArrayList;
import java.util.List;
import net.berkeley.students.eng2024.Room;
import net.berkeley.students.eng2024.Passage;

public class RoomFive{
    public void roomFive(String[] args){

        //List<passage> passages = new ArrayList<>();
        //ArrayList<IItem> itmes = new ArrayList<>();
        
        // Create an instance of the Room1 or Hub
        Room room = new Room("codeName", "passageDescription", "roomDescription", passages, items);

        System.out.println("Room description: " + room.getDescription());
        System.out.println("Passage description: " + room.getPassageDescription());
        System.out.println("Items in the room: " + room.getItems());
    }
}