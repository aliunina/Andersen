import java.math.BigDecimal;
import java.sql.Timestamp;

public class TicketService {
    public static void main(String[] args) {
        Ticket emptyTicket = new Ticket();
        Ticket limitedTicket = new Ticket((short) 2, "MinskArena", (short) 123,
                new Timestamp(1733850000000L));
        Ticket fullTicket = new Ticket((short) 3, "DinamoSt", (short) 472,
                new Timestamp(1740756600000L), true, StadiumSector.B,
                10F, new BigDecimal(100F));

        System.out.println("Empty ticket: " + emptyTicket);
        System.out.println("Limited ticket: " + limitedTicket);
        System.out.println("Full ticket: " + fullTicket);

        System.out.println("\nChanging limited ticket price: ");
        limitedTicket.setPrice(new BigDecimal(380.5F));
        System.out.println("Limited ticket: " + limitedTicket);
    }

    void destroy() {
        System.out.println("2 DESTROY");
    }
}
