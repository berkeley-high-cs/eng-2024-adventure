package net.berkeley.students.eng2024.Procedural;
import java.util.*;
public class Room{
  private HashMap<Direction, Room> connectedRooms = new HashMap<>();
  private final List<Direction> availiblePorts;
  private int id;
  private String roomName;
  
  public Room(List<Direction> availiblePorts){
    this.availiblePorts=availiblePorts;
  }
  
  public Room(String lineFromTextFile){
    List<String> list = Arrays.asList(lineFromTextFile.split("[;]"));
    availiblePorts=direct(list.get(2).replace("(","").replace(")","").split("[,]"));
    this.id=Integer.parseInt(list.get(0));
  }

  private List<Direction> direct(String[] abbreviation){
    List<Direction> direction=new ArrayList<Direction>();
    HashMap<String,Direction> abbreviationToDirection = initializeAbbreviationToDirection();
    for(int i=0;i<abbreviation.length;i++){
      direction.add(abbreviationToDirection.get(abbreviation[i]));
    }
    return direction;
  }
  
  private HashMap<String,Direction> initializeAbbreviationToDirection(){
    HashMap<String,Direction> hm=new HashMap<>();
    //i dont feel like donig some bs to get this
    hm.put("n",Direction.NORTH);
    hm.put("e",Direction.EAST);
    hm.put("s",Direction.SOUTH);
    hm.put("w",Direction.WEST);
    return hm;
  }
  
  public void attach(Room other){
    //change this to get the direction that they're actually connected to
    Direction attachment = findAttachable(other);
    if(!(attachment==null)){
      connectedRooms.put(attachment,other);
      other.getConnectedRooms().put(attachment.getOpposite(),this);
    }
    
    // System.out.println(other.getConnectedRooms().containsValue(this));
    // if(!other.getConnectedRooms().containsValue(this)){other.attach(this);}
  }
  
  private Direction findAttachable(Room other){
    for(Direction availibles:availiblePorts){
      for(Direction avails:other.getAvailiblePorts()){
        if(availibles.equals(avails.getOpposite())){return availibles;}
      }
    }
    //forgot how to avoid null
    return null;
  }

  public List<Direction> getAvailiblePorts(){
    return availiblePorts;
  }

  public HashMap<Direction, Room> getConnectedRooms(){
    return connectedRooms;
  }

  public int getId(){
    return this.id;
  }
}