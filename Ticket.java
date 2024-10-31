package Business;

/**
 * Represents a Ticket with details such as the hour of the event, the table number, and the number of drinks included.
 */
public class Ticket {
    private int hour;
    private int table;
    private int drinks;


    /**
     * Constructs a Ticket instance with the specified hour, table, and number of drinks.
     *
     * @param hour the hour of the event
     * @param table the table number
     * @param drinks the number of drinks included with the ticket
     */
    public Ticket(int hour, int table, int drinks) {
        this.hour = hour;
        this.table = table;
        this.drinks = drinks;
    }

    /**
     * Returns a string representation of the Ticket, including the hour of the event, the table number, and the number of drinks.
     *
     * @return a string describing the Ticket's hour, table, and drinks information
     */
    public String toString() {
        return "\tHora: " + hour + "\n\tMesa: " + table + "\n\tBebidas: " + drinks;
    }

    /**
     * Adds the specified number of drinks to the ticket.
     *
     * @param drink the number of drinks to be added to the ticket
     */
    public void addDrink(int drink){
        drinks += drink;
    }

    /**
     * Retrieves the hour of the event specified in the ticket.
     *
     * @return the hour of the event
     */
    public int getHour() {
        return hour;
    }

    /**
     * Retrieves the table number associated with the ticket.
     *
     * @return the table number
     */
    public int getTable() {
        return table;
    }

    /**
     * Retrieves the number of drinks included with the ticket.
     *
     * @return the number of drinks included
     */
    public int getDrinks() {
        return drinks;
    }
}
