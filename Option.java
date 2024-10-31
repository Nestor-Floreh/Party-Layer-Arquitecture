package Presentation;

/**
 * Enumeration representing various options that can be selected in the application.
 *
 * The options include:
 * LIST_STUDENTS - List all students.
 * VALIDATE_TICKET - Validate a specific ticket.
 * ADD_DRINKS - Add drinks to a participant's ticket.
 * LIST_STUDENTS_FROM_A_FILE - List students from a file.
 * LIST_STUDENTS_FROM_A_TABLE - List students from a specific table.
 * EXIT - Exit the application.
 * ELSE - Represents an invalid or unrecognized option.
 */
public enum Option {
    LIST_STUDENTS, VALIDATE_TICKET, ADD_DRINKS, LIST_STUDENTS_FROM_A_FILE, LIST_STUDENTS_FROM_A_TABLE, EXIT, ELSE;

    /**
     * Converts an integer value to its corresponding Option enum value.
     *
     * @param value the integer value to convert to an Option.
     * @return the corresponding Option enum value. If the value is not within the valid range, returns Option.ELSE.
     */
    public static Option convertIntToEnum(int value) {

        if (value <= 6){
            return Option.values()[value - 1];
        } else {
            return Option.ELSE;
        }

    }
}
