import model.*;
import services.TicketService;

import java.nio.file.Paths;

public class Main extends Identifier {
    public static void main(String[] args) {
        try {
            TicketService ts = new TicketService();
            ts.readTicketsFromFile("src/resources/tickets.json");
        } catch (Exception e) {

        }
    }
}
