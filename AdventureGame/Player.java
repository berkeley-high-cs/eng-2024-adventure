public class Player {

    Room currentRoom;
    double hitpoints;
    final double maxHitpoints = 100;
    AdventureGame adventureGame;

    public Player(Room startRoom, AdventureGame adventureGame) {
        this.currentRoom = startRoom;
        this.adventureGame = adventureGame;
        this.hitpoints = maxHitpoints;
    }

    public Room getRoom() { return this.currentRoom; }
    public double getHitpoints() { return this.hitpoints; }
    public void moveToRoom(Room room) { this.currentRoom = room; }
    public void setHitpoints(double hitpoints) { this.hitpoints = hitpoints; }

}
