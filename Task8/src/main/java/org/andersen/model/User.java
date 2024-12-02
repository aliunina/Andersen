package org.andersen.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "\"User\"", schema = "public")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private long id;

    @Column(nullable = false)
    private String name;

    @CreationTimestamp
    @Column(name = "creation_date", nullable = false)
    private Timestamp creationDate;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ticket> tickets;

    public User() {
        this.creationDate = new Timestamp(System.currentTimeMillis());
    }

    public User(String name) {
        this();
        this.name = name;
    }

    public User(long id, String name) {
        this(name);
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }
}
