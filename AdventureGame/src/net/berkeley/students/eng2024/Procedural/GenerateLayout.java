package net.berkeley.students.eng2024.Procedural;
public class GenerateLayout{
    private static Room[] rooms = new Room[1];
    //private static Room[] rooms = fetchRequiredRooms();
    private static Room[][] map = new Room[9][9];
    private static double[][] weights = {
      {4,4,4,4,4,4,4,4,4},
      {4,3,3,3,3,3,3,3,4},
      {4,3,2,2,2,2,2,3,4},
      {4,3,2,1,1,1,2,3,4},
      {4,3,2,1,0,1,2,3,4},
      {4,3,2,1,1,1,2,3,4},
      {4,3,2,2,2,2,2,3,4},
      {4,3,3,3,3,3,3,3,4},
      {4,4,4,4,4,4,4,4,4}
    };
    
    public static void generateLayout(Room r){
      int[] coords = HeatMap.findHottestRoom(weights,map,r);
      map[coords[0]][coords[1]]=r;
      map[4][4]=new Room("000;center;(n,e,s,w);(items);(monsters)");
    }
  
    //public static generateLayout(){
      //find a room with a door
      //query a room with a matching door and at least one other door
      //recurse
    //}
  
    public static void printMap(){
      for(int i=0;i<map.length;i++){
        System.out.print("[");
        for(int j=0;j<map[0].length;j++){
          if(map[i][j]==null){System.out.print(" ,");}
          else{System.out.print(""+map[i][j].getId()+",");}
        }
        System.out.println("]");
      }
    }
  }