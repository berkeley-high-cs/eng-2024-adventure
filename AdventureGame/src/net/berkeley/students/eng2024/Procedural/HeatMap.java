package net.berkeley.students.eng2024.Procedural;
import java.lang.Math;
public class HeatMap{
  private static double[][] randomizeWeights(double[][] weights){
    for(int i=0;i<weights.length;i++){
      for(int j=0;j<weights[0].length;j++){
        weights[i][j]*=Math.random();
      }
    }
    return weights;
  }
  
  public static int[] findHottestRoom(double[][] weights, Room[][] map, Room room){
    double[][] randomizedWeights=randomizeWeights(weights);
    double bestWeight = 0;
    int[] coords=new int[2];
    for(int i=0;i<randomizedWeights.length;i++){
      for(int j=0;j<randomizedWeights[0].length;j++){
        int[] temp={i,j};
        if(randomizedWeights[i][j]>bestWeight&&map[i][j]==null&&noFinalsNearby(map,temp)&&hasExit(temp,room)){
          bestWeight=randomizedWeights[i][j];
          coords[0]=i;
          coords[1]=j;
        }
      }
      
    }
    
    return coords;
  }
  //fix this
  private static boolean noFinalsNearby(Room[][] map, int[] coords){
    if(map[coords[0]-1>0?coords[0]-1:0][coords[1]]!=null){return false;}
    if(map[coords[0]][coords[1]+1<8?coords[1]+1:8]!=null){return false;}
    if(map[coords[0]][coords[1]-1>0?coords[1]-1:0]!=null){return false;}
    if(map[coords[0]+1<8?coords[0]+1:8][coords[1]]!=null){return false;}
    return true;
  }
  private static boolean hasExit(int[] coords, Room room){
    if(coords[0]==0&&room.getAvailiblePorts().contains(Direction.WEST)){return false;}
    if(coords[0]==8&&room.getAvailiblePorts().contains(Direction.EAST)){return false;}
    if(coords[1]==0&&room.getAvailiblePorts().contains(Direction.NORTH)){return false;}
    if(coords[1]==8&&room.getAvailiblePorts().contains(Direction.SOUTH)){return false;}
    return true;
  }
}