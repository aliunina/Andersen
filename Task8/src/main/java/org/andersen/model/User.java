package org.andersen.model;

import java.util.Date;

public class User {
    private long id;
    private String name;
    private Date creationDate;

    public User(long id, String name, Date creationDate) {
        this.id = id;
        this.name = name;
        this.creationDate = creationDate;
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
