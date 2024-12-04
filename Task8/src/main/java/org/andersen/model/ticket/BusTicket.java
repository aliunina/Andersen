package org.andersen.model.ticket;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BusTicket extends Ticket {
    private String ticketClass;
    private LocalDate startDate;
    private BigDecimal price;

    public BusTicket(String ticketClass, TicketType type, LocalDate startDate, BigDecimal price) {
        this.ticketClass = ticketClass;
        this.type = type;
        this.startDate = startDate;
        this.price = price;
    }
}