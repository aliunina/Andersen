import model.*;
import org.json.JSONArray;
import services.TicketService;

public class Main extends Identifier {
    private static final String TICKETS_PATH = "resources/tickets.json";
    private static final String VALID_TICKETS_PATH = "resources/validtickets.json";

    public static void main(String[] args) {
        TicketService ts = new TicketService();
        JSONArray tickets = ts.readTicketsFromFile(TICKETS_PATH);
        if (tickets != null) {
            ts.validateTickets(tickets);
        }

        System.out.println();
        JSONArray validTickets = ts.readTicketsFromFile(VALID_TICKETS_PATH);
        if (validTickets != null) {
            ts.validateTickets(validTickets);
        }
    }
}
