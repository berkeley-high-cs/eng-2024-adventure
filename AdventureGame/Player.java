public class Player {

    Room currentRoom;
    int hitpoints;
    final int maxHitpoints = 100;
    AdventureGame adventureGame;

    public Player(Room startRoom, AdventureGame adventureGame) {
        this.currentRoom = startRoom;
        this.adventureGame = adventureGame;
        this.hitpoints = maxHitpoints;
    }

    public Room getRoom() { return this.currentRoom; }
    public int getHitpoints() { return this.hitpoints; }
    public void moveToRoom(Room room) { this.currentRoom = room; }
    public void setHitpoints(int hitpoints) { this.hitpoints = hitpoints; }

}
