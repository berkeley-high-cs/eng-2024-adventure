package src.main.java.org.adventure;

import java.io.Console;

public class AdventureGame {
    final Console console = System.console();
    
    private static void takeAction(String action) {
        for (Command command : Command.AvailableCommands()) {
            if (command.ActionIsThisCommand(action)) {
                command.TakeAction(action);
            }
        }
    }

    //make sure all choices in the game are in lower case!
    public String ask(String message) {
        return console.readLine(message).toLowerCase();
    }
}
