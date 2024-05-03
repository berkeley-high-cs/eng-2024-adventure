

import java.io.Console;

public class AdventureGame {

    public static Player player;

    public AdventureGame() {
        String action = ask("What would you like to do?");
        player = new Player(null, this);
        takeAction(action);
    }

    private static final Console console = System.console();
    
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
