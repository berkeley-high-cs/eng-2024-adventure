
import java.io.*;
import java.util.*;

public class Room {
    private IItem[][] grid;
    private String roomDescription;
    private int roomNum;
    private int bottomDoor;
    private int leftDoor;
    private int rightDoor;
    private int topDoor;

    /*
     * public static void main(String[] args){
     * item[][] boy = new item[2][2];
     * //this.room(boy,"h",1,1,1,1,1);
     * //boy[1][1] = "boy";
     * room hu = new room(boy,"h",1,1,1,1,1);
     * System.out.println(hu.grid[1][1].getdis());
     * }
     */
    public room(IItem[][] grid, String description, int num, int bottom, int left, int right, int top){
        this.grid = grid;
        this.roomDescription = description;
        roomNum = num;
        bottomDoor = bottom;
        leftDoor = left;
        rightDoor = right;
        topDoor = top;
    }

    public String getDescription() {
        String list = "";
        for (i = 0; i < 3; i++) {
            for (s = 0; s < 3; s++) {
                list += " " + grid[i][s].description();
            }
        }
        return roomDistckripshon + " " + list;
    }

    public int getBottom() {
        return bottomDoor;
    }

    public int getLeft() {
        return leftDoor;
    }

    public int getRight() {
        return rightDoor;
    }

    public int getTop() {
        return topDoor;
    }

    public item[][] getLayout() {
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

}