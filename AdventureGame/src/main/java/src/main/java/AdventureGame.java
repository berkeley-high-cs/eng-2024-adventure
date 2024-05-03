package src.main.java;


import java.io.Console;
import src.main.java.AdventureGame;
import src.main.java.GameMap;
import src.main.java.Command;

public class AdventureGame {


    private GameMap g = new GameMap();


    public static Player player;

    public AdventureGame() {
        String action = ask("What would you like to do?");
        player = new Player(null, this);
        takeAction(action);
    }

    private static final Console console = System.console();
    
    private static void takeAction(String action) {
        boolean taken = false;
        for (Command command : Command.AvailableCommands()) {
            if (command.ActionIsThisCommand(action)) {
                command.TakeAction(action);
                taken = true;
            } 
        }
        if(!taken){
            System.out.println("Sorry, we don't recognize this command. Try:");
            Command.AvailableCommands().forEach(s -> System.out.print(s + ", "));;
            System.out.println();
        }
    }

    //make sure all choices in the game are in lower case!
    public String ask(String message) {
        return console.readLine(message).toLowerCase();
    }
}
