package Business;
import java.util.ArrayList;

/**
 * Represents a Participant in an event with personal details and ticket information.
 */
public class Participant {
    private String name;
    private String birth;
    private String nationality;
    private int ticketId;
    private int grade;
    private String field;
    private Ticket ticket;

    /**
     * Constructs a Participant instance with the specified details.
     *
     * @param name the name of the participant
     * @param birth the birth date of the participant
     * @param nationality the nationality of the participant
     * @param ticketId the ticket ID of the participant
     * @param grade the grade of the participant
     * @param field the field of study or work of the participant
     * @param haveTicket boolean flag indicating if the participant has a ticket
     * @param hour the hour of the event specified in the ticket
     * @param table the table number specified in the ticket
     * @param drinks the number of drinks included with the ticket
     */
    public Participant(String name, String birth, String nationality, int ticketId, int grade, String field, boolean haveTicket, int hour, int table, int drinks) {
        this.name = name;
        this.birth = birth;
        this.nationality = nationality;
        this.ticketId = ticketId;
        this.grade = grade;
        this.field = field;
        if (haveTicket) {
            ticket = new Ticket(hour, table, drinks);
        }
    }

    /**
     * Returns a string representation of the Participant, including personal details and ticket information if available.
     *
     * @return a string describing the Participant's name, birth date, nationality, ticket ID, grade, field, and ticket details if present
     */
    public String toString() {
        String frase = "\nName: " + name + "\nBirth: " + birth + "\nNationality: " + nationality + "Ticket ID: " + ticketId + "\nGrade: " + grade + "\nField: " + field + "\n";
        if (ticket != null) {
            frase += ticket.toString();
        }

        return frase;
    }

    /**
     * Retrieves the name of the Participant.
     *
     * @return the name of the Participant
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the ticket ID of the Participant.
     *
     * @return the ticket ID of the Participant
     */
    public int getTicketId() {
        return ticketId;
    }

    /**
     * Retrieves the ticket associated with the Participant.
     *
     * @return the ticket of the Participant, or null if no ticket is assigned
     */
    public Ticket getTicket() {
        return ticket;
    }

    /**
     * Adds a specified number of drinks to the participant's ticket.
     *
     * @param drink the number of drinks to be added to the ticket
     */
    public void addDrinkInTicket(int drink){
        if (ticket != null) {
            ticket.addDrink(drink);
        } else {
            System.out.println("The ticket are invalid.");
        }
    }

    /**
     * Retrieves the birth date of the Participant.
     *
     * @return the birth date of the Participant
     */
    public Object getBirth() {
        return birth;
    }

    /**
     * Retrieves the nationality of the Participant.
     *
     * @return the nationality of the Participant
     */
    public Object getNationality() {
        return nationality;
    }

    /**
     * Retrieves the grade of the Participant.
     *
     * @return the grade of the Participant
     */
    public Object getGrade() {
        return grade;
    }

    /**
     * Retrieves the field of study or work of the Participant.
     *
     * @return the field of study or work of the Participant
     */
    public Object getField() {
        return field;
    }
}
