package org.andersen.model;

import java.util.Date;

public class Ticket {
    private long id;
    private long userId;
    private TicketType type;

    private Date creationDate;

    public Ticket(long id, long userId, TicketType type, Date creationDate) {
        this.id = id;
        this.userId = userId;
        this.type = type;
        this.creationDate = creationDate;
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
