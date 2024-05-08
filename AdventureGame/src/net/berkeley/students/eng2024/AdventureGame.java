package net.berkeley.students.eng2024;

import java.io.Console;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

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
        System.out.println(format(type,message));
    }
    public static String format(String type, String message) {
        String str = "";
        switch (type) {
            case "warning":
                str = "!!! " + message + " !!!";
                break;
            case "info":
                str = "< " + message + " >";
                break;
            case "longinfo": 
                ArrayList<String> lines = new ArrayList<String>(message.lines().toList());
                if (lines.size() == 1) {
                    str = "< " + message + " >";
                    break;
                }
                int longest = message.lines().reduce("", (a, b) -> a.length() > b.length() ? a : b).length();
                
                for (int i = 0; i < lines.size(); i++) {
                    String left;
                    String right;
                    if (i == 0) { left = "/ "; right = "\\"; }
                    else if (i == lines.size() - 1) { left = "\\ "; right = "/"; }
                    else { left = "| "; right = "|"; }
                    String pad = longest - lines.get(i).length() <= 0 ? "" : String.valueOf(longest - lines.get(i).length());
                    String format = "%s %s %" + pad + "s %n";
                    lines.set(i,String.format(format,left,lines.get(i), right));
                }
                str = lines.stream().reduce("", (a, b) -> a + b);
                break;
            case "borderless":
                str = message;
                break;
            default:
                str = "--- " + message + " ---";
        }
        return str;
    }

    // make sure all choices in the game are in lower case!
    public String ask(String message) {

        return console.readLine(message).toLowerCase();
    }
}
