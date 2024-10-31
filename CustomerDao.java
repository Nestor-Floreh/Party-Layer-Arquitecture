package Persistence;

import java.io.FileReader;
import Business.Participant;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.io.FileWriter;
import Business.Ticket;

/**
 * CustomerDao provides data access operations related to participants.
 */
public class CustomerDao {

    /**
     * Saves a list of participants and their associated tickets to JSON files.
     *
     * @param participants the list of Participant objects to be saved
     * @throws IOException if an I/O error occurs while writing to the files
     */
    public static void saveParticipants(ArrayList<Participant> participants) throws IOException {
        JSONArray participantsArray = new JSONArray();
        JSONArray ticketsArray = new JSONArray();

        for (Participant participant : participants) {
            JSONObject participantObj = new JSONObject();
            participantObj.put("name", participant.getName());
            participantObj.put("birth", participant.getBirth());
            participantObj.put("nationality", participant.getNationality());
            participantObj.put("ticketId", participant.getTicketId());
            participantObj.put("grade", participant.getGrade());
            participantObj.put("field", participant.getField());

            participantsArray.add(participantObj);

            if (participant.getTicket() != null) {
                JSONObject ticketObj = new JSONObject();
                Ticket ticket = participant.getTicket();
                ticketObj.put("hour", ticket.getHour());
                ticketObj.put("table", ticket.getTable());
                ticketObj.put("drinks", ticket.getDrinks());

                // the ticket should be stored in the tickets array at the same index as participant
                ticketsArray.add(ticketObj);
            } else {
                // if no ticket, add a placeholder for index consistency
                ticketsArray.add(false);
            }
        }

        JSONObject participantsJsonObject = new JSONObject();
        participantsJsonObject.put("participants", participantsArray);

        FileWriter writerParticipants = new FileWriter("C:\\Documentos\\ingenieria informatica\\Segundo_carrera\\Programacion Orientada a Objetos\\javaProjects\\PartyGRASP\\src\\Database\\Sallefest.json");
        FileWriter writerTickets = new FileWriter("C:\\Documentos\\ingenieria informatica\\Segundo_carrera\\Programacion Orientada a Objetos\\javaProjects\\PartyGRASP\\src\\Database\\Tickets.json");

        writerParticipants.write(participantsJsonObject.toJSONString());
        writerTickets.write(ticketsArray.toJSONString());

        writerParticipants.flush();
        writerTickets.flush();
        writerParticipants.close();
        writerTickets.close();
    }
    
    /**
     * Reads participant details from JSON files and returns a list of Participant objects.
     *
     * @return an ArrayList of Participant objects containing information read from the JSON files
     * @throws IOException if an I/O error occurs while reading the JSON files
     * @throws ParseException if there is an error in parsing the JSON data
     */
    public static ArrayList<Participant> readParticipants() throws IOException, ParseException {
        ArrayList<Participant> participants = new ArrayList<>();
        JSONParser parser = new JSONParser();
        int i = 0;

        // Ruta dels fitxers JSON
        FileReader readerParticipants = new FileReader("C:\\Documentos\\ingenieria informatica\\Segundo_carrera\\Programacion Orientada a Objetos\\javaProjects\\PartyGRASP\\src\\Database\\Sallefest.json");
        FileReader readerTickets = new FileReader("C:\\Documentos\\ingenieria informatica\\Segundo_carrera\\Programacion Orientada a Objetos\\javaProjects\\PartyGRASP\\src\\Database\\Tickets.json");

        // Anàlisi del fitxer JSON de participants
        Object objParticipants = parser.parse(readerParticipants);
        JSONObject jsonObjectParticipants = (JSONObject) objParticipants;
        JSONArray participantsArray = (JSONArray) jsonObjectParticipants.get("participants");
        Object objTickets = parser.parse(readerTickets);
        JSONArray ticketsArray = (JSONArray) objTickets;

        System.out.println("Llista de participants:");
        for (Object participantObj : participantsArray) {
            JSONObject participant = (JSONObject) participantObj;

            String name = (String) participant.get("name");
            String birth = (String) participant.get("birth");
            String nationality = (String) participant.get("nationality");
            long ticketId = (long) participant.get("ticketId");
            long grade = (long) participant.get("grade");
            String field = (String) participant.get("field");

            if (ticketsArray.get(i) instanceof JSONObject ticket) {

                long hour = (long) ticket.get("hour");
                long table = (long) ticket.get("table");
                long drinks = (long) ticket.get("drinks");

                participants.add(new Participant(name, birth, nationality, (int) ticketId, (int) grade, field, true, (int) hour, (int) table, (int) drinks));
            } else {
                participants.add(new Participant(name, birth, nationality, (int) ticketId, (int) grade, field, false, 0, 0, 0));
            }

            i++;
        }

        readerParticipants.close();
        readerTickets.close();

        return participants;
    }

}

