import Presentation.Controller;
import org.json.simple.parser.ParseException;

import javax.naming.ldap.Control;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        Controller controller = new Controller();
        controller.run();
    }
}