package org.andersen.model.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.andersen.model.ticket.Ticket;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="users")
@Getter
@Setter
public class User {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @NotNull
    @Column(name = "name")
    private String name;

    @Column(name="creation_date", updatable = false)
    @CreationTimestamp
    private Timestamp creationDate;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Ticket> tickets;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    public User(String name, UserStatus status) {
        this.name = name;
        this.status = status;
        this.creationDate = new Timestamp(System.currentTimeMillis());
    }

    public User(long id, String name, UserStatus status) {
        this(name, status);
        this.id = id;
    }
}
