import model.Identifier;
import model.storage.TicketArrayList;
import model.storage.TicketHashSet;
import model.ticket.StadiumSector;
import model.ticket.Ticket;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Iterator;

public class TicketService extends Identifier {
    public static void main(String[] args) {
        TicketArrayList list = new TicketArrayList();

        //filling storage
        Ticket limitedTicket = new Ticket("0001", "MinskArena", "123", new Timestamp(1733850000000L));
        Ticket fullTicket = new Ticket("0002", "DinamoSt", "472", new Timestamp(1740756600000L),
                true, StadiumSector.B, 10F, new BigDecimal(100F));
        limitedTicket.setPrice(new BigDecimal(380.5F));
        list.add(limitedTicket);
        list.add(fullTicket);
        list.add(new Ticket("0003", "Bolshoi", "537", new Timestamp(1733550000000L)));
        list.add(new Ticket("0004", "Chizhouka", "428", new Timestamp(1735570000000L)));
        list.add(new Ticket("0005", "OperaTh", "034", new Timestamp(1795580000000L)));
        list.add(new Ticket("0006", "MinskArena", "525", new Timestamp(1795690000000L)));
        list.add(new Ticket("0007", "Bolshoi", "765", new Timestamp(1796680000000L)));
        list.add(new Ticket("0008", "DinamoSt", "125", new Timestamp(1794470000000L)));
        list.add(new Ticket("0009", "MinskArena", "908", new Timestamp(1735590000000L)));
        list.add(new Ticket("0010", "OperaTh", "473", new Timestamp(1793760000000L)));

        System.out.println(list);

        list.delete(5);
        list.delete(2);
        list.delete(10);

        System.out.println(list);

        System.out.println(list.getByIndex(2));
        System.out.println(list.getByIndex(13));

        TicketHashSet set = new TicketHashSet();
        Ticket t1 = new Ticket("0001", "Bolshoi", "537", new Timestamp(1733550000000L));
        Ticket t2 = new Ticket("0002", "Chizhouka", "428", new Timestamp(1735570000000L));
        Ticket t3 = new Ticket("0003", "OperaTh", "034", new Timestamp(1795580000000L));
        Ticket t4 = new Ticket("0004", "DinamoSt", "125", new Timestamp(1794470000000L));
        Ticket t5 = new Ticket("0005", "Bolshoi", "765", new Timestamp(1796680000000L));
        Ticket t6 = new Ticket("0006", "MinskArena", "908", new Timestamp(1735590000000L));
        Ticket t7 = new Ticket("0006", "MinskArena", "908", new Timestamp(1735590000000L));

        set.add(t1);
        set.add(t2);
        set.add(t3);
        set.add(t2);
        set.add(t3);
        set.add(t1);
        set.add(t6);
        set.add(t4);
        set.add(t5);
        set.add(t4);
        set.add(t5);
        set.add(t2);
        set.add(t6);

        System.out.println(set);

        System.out.println(set.contains(t4));
        System.out.println(set.contains(t7));
        System.out.println(set.contains(new Ticket("0015", "Std", "123",
                new Timestamp(1732560000000L))));

        set.delete(t3);
        set.delete(t5);
        set.delete(t4);

        Iterator<Ticket> iter = set.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
    }
}
