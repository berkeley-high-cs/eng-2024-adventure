package net.berkeley.students.eng2024;

import java.io.Console;
import java.io.PrintWriter;

public class AdventureGame {

    private GameMap g = new GameMap();


    public static Player player;

    public AdventureGame() {
        String action = ask("What would you like to do?");
        player = new Player(null, this);
        takeAction(action);
    }

    private static final Console console = System.console();
    private static final PrintWriter writer = console.writer();
    
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

    
    public static void notify(String type, String message) {
        switch (type) {
            case "warning": writer.println("!!!" + message + "!!!"); return;
            default: writer.println("---" + message + "---"); return;
        }
        
    }

    //make sure all choices in the game are in lower case!
    public String ask(String message) {
        return console.readLine(message).toLowerCase();
    }
}
