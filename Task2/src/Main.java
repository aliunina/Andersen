import model.*;
import org.json.JSONArray;
import services.TicketService;

public class Main extends Identifier {
    public static void main(String[] args) {
        TicketService ts = new TicketService();
        JSONArray tickets = ts.readTicketsFromFile("src/resources/tickets.json");
        if (tickets != null) {
            ts.validateTickets(tickets);
        }

        System.out.println();
        JSONArray validTickets = ts.readTicketsFromFile("src/resources/validtickets.json");
        if (validTickets != null) {
            ts.validateTickets(validTickets);
        }
    }
}
