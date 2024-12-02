package org.andersen.model;

import java.sql.Timestamp;
import java.util.Date;

public class Ticket {
    private long id;
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
        this.userId = userId;
        this.type = type;
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
