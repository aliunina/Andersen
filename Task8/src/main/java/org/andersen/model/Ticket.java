package org.andersen.model;

import jakarta.persistence.*;

import java.util.Date;

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

    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    public Ticket() {
        this.creationDate = new java.sql.Date(System.currentTimeMillis());
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
