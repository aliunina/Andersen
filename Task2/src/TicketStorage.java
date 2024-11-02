import javax.management.InstanceNotFoundException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class TicketStorage extends TicketClass {
	private ArrayList<Ticket> tickets = new ArrayList();

	public Ticket addTicket(Ticket ticket) {
		tickets.add(ticket);
		return ticket;
	}

	public Ticket getTicketById(short id) throws InstanceNotFoundException {
		return tickets.stream().filter(ticket -> ticket.getId() == id).findFirst()
				.orElseThrow(() -> new InstanceNotFoundException());
	}

	public List<Ticket> getTicketByStadiumSector(StadiumSector stadiumSector) {
		return tickets.stream().filter(ticket -> ticket.getStadiumSector() == stadiumSector).toList();
	}

	public List<Ticket> getAllTickets() {
		return tickets;
	}

	@Override
	public void print() {
		super.print();
		System.out.println("Tickets: ");
		for (Ticket ticket : getAllTickets()) {
			System.out.println(ticket);
		}
	}
}
