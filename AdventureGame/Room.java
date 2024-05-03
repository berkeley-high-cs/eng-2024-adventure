
import java.util.HashMap;
import java.util.Map;

public class Room {
    private IItem[][] grid;
    private String roomDescription;
    private String codeName;
    private HashMap<String, Room> passages;

    public Room(String codeName, String description, IItem[][] grid){
        this.codeName = codeName;
        this.grid = grid;
        this.roomDescription = description;
    }

    public void addPassage(String name, Room room) {
        passages.put(name,room);
    }
    public List getPassageNames() { return passages.keySet(); }
    public Room getConnectingRoom(String passageName) { return passages.get(passageName); }


    public String getDescription() {
        String list = "";
        for (i = 0; i < 3; i++) {
            for (s = 0; s < 3; s++) {
                list += " " + grid[i][s].description();
            }
        }
        return roomDescription + " " + list;
    }

    public IItem[][] getLayout() {
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

    public String getName() { return codeName; }

}