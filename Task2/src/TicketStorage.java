import java.util.ArrayList;

public class TicketStorage {
    private static ArrayList<Ticket> tickets = new ArrayList<Ticket>();

    public static Ticket addTicket(Ticket ticket) {
        tickets.add(ticket);
        return ticket;
    }
}
