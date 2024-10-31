package Presentation;

import Business.ParticipantManager;
import org.json.simple.parser.ParseException;

import java.io.IOException;

/**
 * The Controller class manages the interaction between the user and the participant management system.
 * It handles user input, performs operations based on selected options, and communicates with other components.
 */
public class Controller {
    private final InputOutputController inputOutputController;
    private final ParticipantManager participantManager;

    /**
     * Initializes a new Controller instance.
     * This constructor sets up the necessary controllers for handling I/O operations
     * and managing participants.
     *
     * @throws IOException if an I/O error occurs during initialization.
     * @throws ParseException if a parsing error occurs during initialization.
     */
    public Controller() throws IOException, ParseException {
        this.inputOutputController = new InputOutputController();
        participantManager = new ParticipantManager();
    }

    /**
     * Executes the selected option by invoking the corresponding method.
     *
     * @param option The selected option to execute.
     */
    private void executeOption(Option option) {
        switch (option) {
            case LIST_STUDENTS -> listStudents();
            case VALIDATE_TICKET -> validateTicket();
            case ADD_DRINKS -> addDrink();
            case LIST_STUDENTS_FROM_A_FILE -> listStudentsFromField();
            case LIST_STUDENTS_FROM_A_TABLE -> listStudentsFromTable();
            case EXIT -> exit();
            case ELSE -> System.out.println("Invalid option!!");
        }
    }

    /**
     * Exits the application by saving the participants' data and printing a goodbye message.
     * This method is invoked when the user chooses the exit option from the menu.
     * It ensures that any changes made to the participants' data are persisted before the application terminates.
     */
    private void exit() {
        participantManager.saveParticipantsData();
        System.out.println("Goodbye");
    }

    /**
     * Retrieves and prints the list of participants' names.
     * This method utilizes the participantManager instance to obtain
     * the list of all participants and outputs it directly to the console.
     */
    private void listStudents() {
        String participantNames = participantManager.listOfParticipants();
        System.out.println(participantNames);
    }

    /**
     * Validates a ticket entered by the user.
     * Prompts the user to enter a ticket ID, then checks if a participant with
     * the given ticket ID has a valid ticket. Displays a message indicating whether
     * the ticket is valid or invalid.
     */
    private void validateTicket() {
        int ticketID = InputOutputController.askInteger("Introduce the ticket id: ");
        if (participantManager.haveTicket(ticketID)) {
            System.out.println("\tThe ticket is valid");
        } else {
            System.out.println("\tThe ticket is invalid");
        }
    }

    /**
     * Adds a specified number of drinks to a participant's ticket identified by the provided ticket ID.
     * This method prompts the user to enter the ticket ID and the number of drinks to add.
     * It then calls the ParticipantManager to update the ticket with the specified number of drinks.
     */
    private void addDrink() {
        int ticketID = InputOutputController.askInteger("Introduce the ticket id: ");
        int numDrinks = InputOutputController.askInteger("Introduce the number of drinks: ");
        participantManager.addDrink(ticketID, numDrinks);
    }

    /**
     * Lists and displays students based on a specified field.
     * <pre>
     * This method prompts the user to enter the name of the field.
     * It then retrieves and prints the list of students who are associated
     * with that specific field using the participantManager.
     * </pre>
     */
    private void listStudentsFromField() {
        String fieldName = InputOutputController.askString("Enter the field name: ");
        String participantFromField = participantManager.listStudentsFromField(fieldName);
        System.out.println(participantFromField);
    }

    /**
     * Prompts the user to enter a table number and then lists the students seated at the specified table.
     * The table number is obtained via user input, and the students are fetched from the participant manager.
     * The result is displayed on the console.
     */
    private void listStudentsFromTable() {
        int table = InputOutputController.askInteger("Enter the table number: ");
        String participantFromTable = participantManager.listStudentsFromTable(table);
        System.out.println(participantFromTable);
    }

    /**
     * Continuously displays a menu and processes user input until the user chooses to exit.
     *
     * The method works in a loop, where it:
     * 1. Displays a menu of options using the inputOutputController.
     * 2. Prompts the user to select an option.
     * 3. Converts the selected option to an appropriate enum value.
     * 4. Executes the corresponding operation based on the selected option.
     *
     * The loop terminates when the user selects the exit option, denoted by the integer value 6.
     */
    public void run(){
        int option;

        do {
            inputOutputController.showMenu();
            option = InputOutputController.askInteger("\nIntroduce an opiton: ");
            executeOption(Option.convertIntToEnum(option));
        } while (option != 6);
    }

}
