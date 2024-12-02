package org.andersen.model;

import java.sql.Timestamp;
import java.util.Date;

public class User {
    private long id;
    private String name;
    private Timestamp creationDate;

    public User(long id, String name) {
        this.id = id;
        this.name = name;
        this.creationDate = new Timestamp(System.currentTimeMillis());
    }

    public User(long id, String name, Timestamp creationDate) {
        this.id = id;
        this.name = name;
        this.creationDate = creationDate;
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
