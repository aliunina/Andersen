import java.math.BigDecimal;
import java.sql.Timestamp;

public class TicketService {
    public static void main(String[] args) {
        Ticket emptyTicket = new Ticket();
        Ticket limitedTicket = new Ticket((short) 1, "MinskArena", (short) 123,
                new Timestamp(1733850000000L));
        Ticket fullTicket = new Ticket((short) 2, "DinamoSt", (short) 472,
                new Timestamp(1740756600000L), true, StadiumSector.B,
                10F, new BigDecimal(100F));

        System.out.println("Empty ticket: " + emptyTicket);
        System.out.println("Limited ticket: " + limitedTicket);
        System.out.println("Full ticket: " + fullTicket);

        System.out.println("\nChanging limited ticket price: ");
        limitedTicket.setPrice(new BigDecimal(380.5F));
        System.out.println("Limited ticket: " + limitedTicket);

        //filling storage
        TicketStorage.addTicket(limitedTicket);
        TicketStorage.addTicket(fullTicket);
        TicketStorage.addTicket(new Ticket((short) 3, "Bolshoi", (short) 537, new Timestamp(1733550000000L)));
        TicketStorage.addTicket(new Ticket((short) 4, "Chizhouka", (short) 428, new Timestamp(1735570000000L)));
        TicketStorage.addTicket(new Ticket((short) 5, "OperaTh", (short) 034, new Timestamp(1795580000000L)));
        TicketStorage.addTicket(new Ticket((short) 6, "MinskArena", (short) 525, new Timestamp(1795690000000L)));
        TicketStorage.addTicket(new Ticket((short) 7, "Bolshoi", (short) 765, new Timestamp(1796680000000L)));
        TicketStorage.addTicket(new Ticket((short) 8, "DinamoSt", (short) 125, new Timestamp(1794470000000L)));
        TicketStorage.addTicket(new Ticket((short) 9, "MinskArena", (short) 908, new Timestamp(1735590000000L)));
        TicketStorage.addTicket(new Ticket((short) 10, "OperaTh", (short) 473, new Timestamp(1793760000000L)));
    }
}
