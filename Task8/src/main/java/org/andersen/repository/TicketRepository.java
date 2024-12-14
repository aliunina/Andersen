package org.andersen.repository;

import org.andersen.model.ticket.Ticket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, Long> {
    List<Ticket> findTicketsByUserId(Long userId);
}
