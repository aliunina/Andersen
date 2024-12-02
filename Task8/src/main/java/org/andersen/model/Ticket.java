package org.andersen.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "\"Ticket\"", schema = "public")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "ticket_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private TicketType ticketType;

    @CreationTimestamp
    @Column(name = "creation_date", nullable = false)
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
        this.id = id;
    }

    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
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
