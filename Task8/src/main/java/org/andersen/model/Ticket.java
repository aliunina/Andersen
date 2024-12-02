package org.andersen.model;
import java.sql.Timestamp;
import java.util.Date;

public class Ticket {
    private long id;
    private User user;
    private TicketType ticketType;
    private Timestamp creationDate;

    public Ticket() {
        this.creationDate = new Timestamp(System.currentTimeMillis());
    }

    public Ticket(User user, TicketType ticketType) {
        this();
        this.ticketType = ticketType;
        this.user = user;
    }

    public Ticket(long id, User user, TicketType ticketType) {
        this(user, ticketType);
    private long userId;
    private TicketType type;
    private Timestamp creationDate;

    public Ticket(long id, long userId, TicketType type) {
        this.id = id;
        this.userId = userId;
        this.type = type;
        this.creationDate = new Timestamp(System.currentTimeMillis());
    }

    public Ticket(long id, long userId, TicketType type, Timestamp creationDate) {
        this.id = id;
    }

    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
    }

    public long getId() {
        return id;
    }

    public long getUserId() {
        return userId;
    }

    public TicketType getType() {
        return type;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", type=" + ticketType +
                ", creationDate=" + creationDate +
                '}';
    }
}
