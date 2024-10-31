package Business;
import Persistence.CustomerDao;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Manages a collection of Participants for an event. Provides methods to add participants, list participants,
 * check ticket status, add drinks to a ticket, and save participant data.
 */
public class ParticipantManager {
    private ArrayList<Participant> participants;

    /**
     * Constructs a new ParticipantManager.
     *
     * This constructor initializes the participant list and populates it
     * with data read from an external data source using the CustomerDao.readParticipants() method.
     *
     * @throws IOException if an I/O error occurs while reading participant data.
     * @throws ParseException if there is an error in parsing the participant data.
     */
    public ParticipantManager() throws IOException, ParseException {
        participants = new ArrayList<>();
        participants = CustomerDao.readParticipants();
    }

    /**
     * Adds a participant to the registered participants list.
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
    public void addParticipant(String name, String birth, String nationality, int ticketId, int grade, String field, boolean haveTicket, int hour, int table, int drinks) {
        Participant participant = new Participant(name, birth, nationality, ticketId, grade, field, haveTicket, hour, table, drinks);
        participants.add(participant);
    }

    /**
     * Generates a string containing the list of participants' names.
     *
     * @return a string representation of all participants' names, each on a new line
     */
    public String listOfParticipants() {
        String participantsList = "\nParticipants:\n";
        for (Participant participant : participants) {
            participantsList += participant.getName() + "\n";
        }
        return participantsList;
    }

    /**
     * Checks if a participant with the specified ticket ID has a valid ticket.
     *
     * @param idTicket the ID of the ticket to be checked
     * @return true if a participant with the given ticket ID has a valid ticket, false otherwise
     */
    public boolean haveTicket(int idTicket) {
        for (Participant participant : participants) {
            if (participant.getTicketId() == idTicket) {
                if (participant.getTicket() != null) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Adds a specified number of drinks to the participant's ticket identified by the provided ticket ID.
     *
     * @param idTicket the ID of the ticket to which drinks should be added
     * @param numDrink the number of drinks to add to the ticket
     */
    public void addDrink(int idTicket, int numDrink) {
        boolean find = false;

        for (Participant participant : participants) {
            if (participant.getTicketId() == idTicket) {
                participant.addDrinkInTicket(numDrink);
                find = true;
            }
        }

        if (!find) {
            System.out.println("The ticket doesnt not exist");
        }
    }

    /**
     * Saves the data of participants to the storage medium.
     * This method triggers the handleSaveData method with the current list of participants.
     */
    public void saveParticipantsData() {
        handleSaveData(participants);
    }

    /**
     * Handles the saving of participant data by delegating to the CustomerDao.
     * Catches and logs any IOExceptions that may occur during the save process.
     *
     * @param participants the list of participants to be saved
     */
    private void handleSaveData(ArrayList<Participant> participants) {
        try {
            CustomerDao.saveParticipants(participants);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Lists students from a specific field.
     *
     * @param field the field of study or work to filter the participants by
     * @return a string containing the names of the participants from the specified field
     */
    public String listStudentsFromField(String field) {
        String participantsList = "\nParticipants from " + field + ":\n\t";
        boolean find = false;

        for (Participant participant : participants) {
            if (participant.getField().equals(field)) {
                participantsList += participant.getName() + "\n\t";
                find = true;
            }
        }
        if (!find) {
            System.out.println("The field doesnt not exist");
        }
        return participantsList;
    }

    /**
     * Generates a list of participants seated at a specified table.
     *
     * @param table the table number for which participants should be listed
     * @return a string listing the names of participants seated at the specified table, or a message indicating that the table does not exist
     */
    public String listStudentsFromTable(int table) {
        String participantsList = "\nParticipants from table " + table + ":\n\t";
        boolean find = false;

        for (Participant participant : participants) {
            if (participant.getTicket() != null) {
                if (participant.getTicket().getTable() == table) {
                    participantsList += participant.getName() + "\n\t";
                    find = true;
                }
            }
        }
        if (!find) {
            System.out.println("The table doesnt not exist");
        }

        return participantsList;
    }
}
