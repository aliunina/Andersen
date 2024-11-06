package model.ticket;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public class BusTicket extends Ticket{
    private String ticketClass;
    private TicketType ticketType;
    private LocalDate startDate;

    public BusTicket(String ticketClass, TicketType ticketType, LocalDate startDate, BigDecimal price) {
        this.ticketClass = ticketClass;
        this.ticketType = ticketType;
        this.startDate = startDate;
        this.setPrice(price);
    }
}
