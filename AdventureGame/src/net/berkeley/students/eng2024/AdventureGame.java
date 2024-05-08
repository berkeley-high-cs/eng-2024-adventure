package net.berkeley.students.eng2024;

import java.io.Console;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class AdventureGame {

    public static final GameMap map = new GameMap();
    private static final Console console = System.console();
    private static final PrintWriter writer = console.writer();
    public static final Player player = new Player(map.getRooms()[0]);;
    private static ArrayList<Command> commands = new ArrayList<>();

    public AdventureGame() {
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
        commands.add(new Command.InspectCommand(player));
        commands.add(new Command.MoveCommand(player));
        
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
            String s = "Sorry, we don't recognize this command. Try:  ";
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
        ArrayList<String> lines = new ArrayList<String>(message.lines().toList());
        switch (type) {
            case "warning":
                str = "!!! " + message + " !!!";
                break;
            case "info":
                str = "< " + message + " >";
                break;
            case "longinfo": 
                if (lines.size() == 1) {
                    str = "< " + message + " >\n";
                    break;
                }
                int longest = message.lines().reduce("", (a, b) -> a.length() > b.length() ? a : b).length();
                for (int i = 0; i < lines.size(); i++) {
                    String left;
                    String right;
                    if (i == 0) { left = " /"; right = "\\"; }
                    else if (i == lines.size() - 1) { left = " \\"; right = "/"; }
                    else { left = "| "; right = " |"; }
                    String pad = longest - lines.get(i).length() <= 0 ? "" : String.valueOf(longest - lines.get(i).length() + 1);
                    String format = "%s %s %" + pad + "s %n";
                    lines.set(i,String.format(format,left,lines.get(i), right));
                }
                str = lines.stream().reduce("", (a, b) -> a + b);
                break;
            case "borderless":
                str = message;
                break;
            default:
                str = lines.stream().map(s -> "--- " + s + " ---\n").reduce("", (a, b) -> a + b);
        }
        return str;
    }

    // make sure all choices in the game are in lower case!
    public String ask(String message) {

        return console.readLine(message).toLowerCase();
    }
}
