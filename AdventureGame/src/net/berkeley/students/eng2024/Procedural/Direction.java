package net.berkeley.students.eng2024.Procedural;
enum Direction{
    NORTH,
    EAST,
    SOUTH,
    WEST;
    
    public Direction getOpposite(){
      return Direction.values()[(this.ordinal()+2)%4];
    }
  }
  
  