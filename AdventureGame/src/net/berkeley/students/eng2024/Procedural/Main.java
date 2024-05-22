package net.berkeley.students.eng2024.Procedural;
import java.util.*;
import java.io.*;
public class Main {
  public static void main(String[] args) throws IOException{
    BufferedReader sc = new BufferedReader(new FileReader("src/main/java/Rooms.txt"));
    try {
      GenerateRooms.generateRooms(0);
    } catch(IOException e){System.out.println("oopsie poopsie");}
    // Direction[] r1s = {Direction.NORTH,Direction.SOUTH};
    // List<Direction> r1dirs = Arrays.asList(r1s);
    // Room r1 = new Room(r1dirs);
    // Direction[] r2s = {Direction.SOUTH,Direction.NORTH};
    // List<Direction> r2dirs = Arrays.asList(r2s);
    // Room r2 = new Room(r2dirs);
    // r1.attach(r2);
    // System.out.println(r1.getConnectedRooms());
    // System.out.println(r2.getConnectedRooms());
    try{
      for(int i=0;i<100;i++){
        String temp = sc.readLine();
        if(temp!=null){
          GenerateLayout.generateLayout(new Room(temp));
        }
      //fill in gaps
      GenerateLayout.generateLayout();
      }
    }
    catch(IOException e){;}
    GenerateLayout.printMap();
    sc.close();
  }
  
}