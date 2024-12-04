package org.andersen.model.user;

import org.andersen.model.ticket.Ticket;

import java.sql.Timestamp;
import java.util.List;

public class User {
    private long id;
    private String name;
    private Timestamp creationDate;
    private List<Ticket> tickets;
    private UserStatus status;

    public User(long id, String name, Timestamp creationDate, UserStatus status) {
        this.id = id;
        this.name = name;
        this.creationDate = creationDate;
        this.status = status;
    }

    public User(long id, String name, Timestamp creationDate, UserStatus status, List<Ticket> tickets) {
        this(id, name, creationDate, status);
        this.tickets = tickets;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public UserStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }
}
