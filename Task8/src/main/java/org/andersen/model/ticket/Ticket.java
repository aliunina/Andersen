package org.andersen.model.ticket;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.andersen.model.user.User;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.cglib.core.Local;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="tickets")
@Getter
@Setter
public class Ticket {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @NotNull
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private TicketType type;

    @Column(name = "class")
    @Pattern(regexp = "[A-Z]{3}")
    private String ticketClass;

    @Column(name="creation_date", updatable = false)
    @CreationTimestamp
    private Timestamp creationDate;

    @Column(name="start_date")
    private LocalDate startDate;

    @Column(name = "price", nullable = false)
    @Positive
    private BigDecimal price;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    protected User user;

    public Ticket(String ticketClass, TicketType type, LocalDate startDate, BigDecimal price) {
        this.ticketClass = ticketClass;
        this.type = type;
        this.startDate = startDate;
        this.price = price;
    }

}
