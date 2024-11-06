import model.*;
import model.ticket.StadiumSector;
import model.ticket.Ticket;
import model.ticket.TicketStorage;
import model.user.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class TicketService extends Identifier {
    public static void main(String[] args) {
        TicketStorage ticketStorage = new TicketStorage();

        //filling storage
        Ticket limitedTicket = new Ticket("0001", "MinskArena","123", new Timestamp(1733850000000L));
        Ticket fullTicket = new Ticket("0002", "DinamoSt", "472", new Timestamp(1740756600000L),
                true, StadiumSector.B, 10F, new BigDecimal(100F));
        limitedTicket.setPrice(new BigDecimal(380.5F));
        ticketStorage.addTicket(limitedTicket);
        ticketStorage.addTicket(fullTicket);
        ticketStorage.addTicket(new Ticket("0003", "Bolshoi", "537", new Timestamp(1733550000000L)));
        ticketStorage.addTicket(new Ticket("0004", "Chizhouka", "428", new Timestamp(1735570000000L)));
        ticketStorage.addTicket(new Ticket("0005", "OperaTh", "034", new Timestamp(1795580000000L)));
        ticketStorage.addTicket(new Ticket("0006", "MinskArena",  "525", new Timestamp(1795690000000L)));
        ticketStorage.addTicket(new Ticket("0007", "Bolshoi", "765", new Timestamp(1796680000000L)));
        ticketStorage.addTicket(new Ticket("0008", "DinamoSt", "125", new Timestamp(1794470000000L)));
        ticketStorage.addTicket(new Ticket("0009", "MinskArena", "908", new Timestamp(1735590000000L)));
        ticketStorage.addTicket(new Ticket("0010", "OperaTh", "473", new Timestamp(1793760000000L)));

        System.out.println();
        limitedTicket.share("+375441231234");
        fullTicket.share("+375255678900", "email@gmail.com");

        System.out.println();
        Client client = new Client();
        Admin admin = new Admin();

        client.printRole();
        client.getTicket(limitedTicket);

        admin.printRole();
        admin.checkTicket(ticketStorage, limitedTicket);

        System.out.println();
        Ticket ticket = new Ticket();
        Ticket nullTicket1 = new Ticket("0011", "MinskArena", null,
                new Timestamp(1733850000000L));
        Ticket nullTicket2 = new Ticket(null, "DinamoSt", "472",
                new Timestamp(1740756600000L), true, StadiumSector.B,
                10F, new BigDecimal(100F));
    }
}
