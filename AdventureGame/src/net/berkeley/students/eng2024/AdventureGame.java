package net.berkeley.students.eng2024;

import java.io.Console;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class AdventureGame {

    private GameMap g = new GameMap();
    private static final Console console = System.console();
    private static final PrintWriter writer = console.writer();
    public static Player player;
    public static GameMap map;
    public static ArrayList<Command> commands = new ArrayList<>();

    public AdventureGame() {

        map = new GameMap();
        player = new Player(map.getRooms()[0], this);
        registerCommands();
        player.moveToRoom(player.getRoom());
        gameLoop();

    }

    // sort by precedence here
    private void registerCommands() {
        commands.add(new Command.ReturnCommand(player));
        commands.add(new Command.AttackCommand(player));
        commands.add(new Command.PickupCommand(player));
        commands.add(new Command.DropCommand(player));
        commands.add(new Command.MoveCommand(player));
        commands.add(new Command.InspectCommand(player));
    }

    private void gameLoop() {
        while (true) {
            String action = ask("What would you like to do?\n");
            takeAction(action);
        }
    }

    private static void takeAction(String action) {
        boolean taken = false;

        for (Command command : commands) {
            if (command.doCommand(action)) {
                taken = true;
                break;
            }
        }
        if (!taken) {
            String s = "Sorry, we don't recognize this command. Try:\n";
            for (Command command : commands) {
                s += command.toString() + " | ";
            }
            notify("warning", s);
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
            case "borderless":
                str = message;
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
