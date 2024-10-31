import javax.management.InstanceNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class TicketStorage {
	private static ArrayList<Ticket> tickets = new ArrayList<Ticket>();

	public static Ticket addTicket(Ticket ticket) {
		tickets.add(ticket);
		return ticket;
	}

	public static Ticket getTicketById(short id) throws InstanceNotFoundException {
		return tickets.stream().filter(ticket -> ticket.getId() == id).findFirst()
				.orElseThrow(() -> new InstanceNotFoundException());
	}

	public static List<Ticket> getTicketByStadiumSector(StadiumSector stadiumSector) {
		return tickets.stream().filter(ticket -> ticket.getStadiumSector() == stadiumSector).toList();
	}
}
