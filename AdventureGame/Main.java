import java.io.Console;
import java.util.List;

class Main {

    private static final Console console = System.console();
    
    private static void takeAction(String action) {
        for (Command command : Command.AvailableCommands()) {
            if (command.ActionIsThisCommand(action)) {
                command.TakeAction(action);
            }
        }
    }

    //make sure all choices in the game are in lower case!
    public static String ask(String message) {
        return console.readLine(message).toLowerCase();
    }

    public static void main(String[] args) {
        String action = ask("What do you want to do?");
        takeAction(action);
    }
}