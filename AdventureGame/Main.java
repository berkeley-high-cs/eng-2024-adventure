import java.io.Console;
import java.util.List;

class Main {

    private static final Console console = System.console();

    public static String askChoices(String message, List<String> responses, boolean printResponses) {
        System.out.println(message + "\n" + (printResponses ? responses.toString() : ""));
        while (true) {
            String answer = ask("");
            if (responses.contains(answer)) {
                return answer;
            }
            else {
                System.out.println("Invalid response!");
            }
        }
    }

    //make sure all choices in the game are in lower case!
    public static String ask(String message) {
        return console.readLine(message).toLowerCase();
    }

    public static void main(String[] args) {

    }
}