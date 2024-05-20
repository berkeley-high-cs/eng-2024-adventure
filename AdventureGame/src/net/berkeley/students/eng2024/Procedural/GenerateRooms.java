package net.berkeley.students.eng2024.Procedural;
import java.io.*;
import java.util.*;
import java.lang.*;
public class GenerateRooms{
  
  public static void generateRooms(int numberOfRooms) throws IOException{
    BufferedWriter sc = new BufferedWriter(new FileWriter("src/main/java/Rooms.txt",true));
    for(int i=0;i<numberOfRooms;i++){
      List<String> dirs=generateDirections();
      sc.append(getRoomId(i)+";room"+i+"name;("+dirs.toString().substring(1,dirs.toString().length()-1).replace(" ","")+");(items);(monsters)");
      sc.append("\n");
    }
    sc.close();
  }
  private static String getRoomId(int i){
    String newId = "";
    if(i<100){newId+="0";}
    if(i<10){newId+="0";}
    return(newId+i);
  }
  
  private static List<String> generateDirections(){
    List<String> generatedDirections = new ArrayList<String>();
    String[] directions = {"n","e","s","w"};
    for(int i=0;i<4;i++){
      String generatedDirection = randomToDirection((int)(Math.random()*4),directions[i]);
      if(!generatedDirection.equals(" ")){generatedDirections.add(generatedDirection);}
    }
    if(generatedDirections.isEmpty()){generatedDirections.add(directions[(int)(Math.random()*4)]);}
    return generatedDirections;
  }
  private static String randomToDirection(int randomNumber, String direction){
    if(randomNumber>1) return direction;
    return " ";
  }
}