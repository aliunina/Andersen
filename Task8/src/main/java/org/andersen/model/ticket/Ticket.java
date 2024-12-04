package org.andersen.model.ticket;

import java.sql.Timestamp;

public class Ticket {
    protected long id;
    protected long userId;
    protected TicketType type;
    protected Timestamp creationDate;

    public Ticket() {
        this.creationDate = new Timestamp(System.currentTimeMillis());
    }

    public Ticket(long userId, TicketType type) {
        this();
        this.userId = userId;
        this.type = type;
    }

    public Ticket(long id, long userId, TicketType type) {
        this();
        this.id = id;
        this.userId = userId;
        this.type = type;
    }

    public Ticket(long id, long userId, TicketType type, Timestamp creationDate) {
        this(id, userId, type);
        this.creationDate = creationDate;
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
                ", userId=" + userId +
                ", type=" + type +
                ", creationDate=" + creationDate +
                '}';
    }
}
