package org.andersen.model;

import java.sql.Timestamp;
import java.util.List;

public class User {
    private long id;

    private String name;

    private Timestamp creationDate;

    private List<Ticket> tickets;

    public User() {
        this.creationDate = new Timestamp(System.currentTimeMillis());
    }

    public User(String name) {
        this();
        this.name = name;
    }

    public User(long id, String name) {
        this.id = id;
        this.name = name;
        this.creationDate = new Timestamp(System.currentTimeMillis());
    }

    public User(long id, String name, Timestamp creationDate) {
        this.id = id;
    }

    public long getId() {
        return this.id;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }
}
