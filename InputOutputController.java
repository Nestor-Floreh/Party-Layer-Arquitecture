package Presentation;
import java.util.Scanner;


/**
 * The InputOutputController class handles input and output operations
 * for interacting with the user through the console.
 */
public class InputOutputController {
    /**
     * A static final instance of Scanner used to read input from the standard input stream (console).
     */
    private static final Scanner sc = new Scanner(System.in);

    /**
     * Prompts the user to enter an integer based on the provided prompt message.
     * If the user enters an invalid value, the method re-prompts until
     * a valid integer is entered.
     *
     * @param prompt The message to display to the user when prompting for input.
     * @return The integer value entered by the user.
     */
    public static int askInteger(String prompt){
        int integer;

        try{
            System.out.print(prompt);
            integer = Integer.parseInt(sc.nextLine());
        }
        catch (Exception e) {
            System.out.println("\tInvalid option!!\n");
            return askInteger(prompt);
        }

        return integer;
    }

    /**
     * Prompts the user for a string input using the provided prompt message.
     *
     * @param prompt the message to display to the user before input
     * @return the string input entered by the user
     */
    public static String askString(String prompt){
        String string;
        System.out.print(prompt);
        string = sc.nextLine();
        return string;
    }

    /**
     * Displays the main menu for the SalleFest application.
     * This method prints a list of menu options to the console, allowing the user to
     * navigate through different functionalities like listing students, validating tickets,
     * adding drinks, and more. The menu options are numbered from 1 to 6, with the final
     * option being the exit command.
     */
    public void showMenu() {
        System.out.println("\n--- SalleFest ---");
        System.out.println("1. List students");
        System.out.println("2. Validate ticket");
        System.out.println("3. Add drinks");
        System.out.println("4. Lists students from a field");
        System.out.println("5. Lists students from a table");
        System.out.println("6. Exit");
    }
}
