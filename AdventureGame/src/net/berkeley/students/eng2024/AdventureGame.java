package net.berkeley.students.eng2024;

import java.io.Console;
import java.io.PrintWriter;

public class AdventureGame {

    private GameMap g = new GameMap();
    private static final Console console = System.console();
    private static final PrintWriter writer = console.writer();
    public static Player player;
    public static GameMap map;

    public AdventureGame() {
        map = new GameMap();
        player = new Player(map.getRooms()[0], this);
        gameLoop();

    }

    private void gameLoop() {
        while (true) {
            String action = ask("What would you like to do?\n");
            takeAction(action);
        }
    }

    private static void takeAction(String action) {
        boolean taken = false;

        for (Command command : Command.AvailableCommands()) {
            if (command.ActionIsThisCommand(action)) {

                command.TakeAction(action);
                taken = true;
            }
        }
        if (!taken) {
            System.out.println("Sorry, we don't recognize this command. Try:");
            Command.AvailableCommands().forEach(s -> System.out.print(s + ", "));
            ;
            System.out.println();
        }
    }

    public static void notify(String type, String message) {
        String str = "";
        switch (type) {
            case "warning":
                str = "!!! " + message + " !!!";
                break;
            case "info":
                str = "< " + message + " >";
                break;
            default:
                str = "--- " + message + " ---";
        }
        System.out.println(str);

    }

    // make sure all choices in the game are in lower case!
    public String ask(String message) {

        return console.readLine(message).toLowerCase();
    }
}
