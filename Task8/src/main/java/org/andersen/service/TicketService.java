package org.andersen.service;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.andersen.exception.TicketNotFoundException;
import org.andersen.exception.UserNotFoundException;
import org.andersen.model.ticket.*;
import org.andersen.model.user.User;
import org.andersen.repository.TicketRepository;
import org.andersen.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final UserRepository userRepository;
    private final TicketRepository ticketRepository;

    @Transactional
    public Ticket addTicket(Ticket ticket) {
        User user = userRepository.findById(ticket.getUser().getId()).orElseThrow(()->new UserNotFoundException());
        ticket.setUser(user);
        return ticketRepository.save(ticket);
    }

    @Transactional(readOnly = true)
    public Ticket getTicketById(Long id) {
        return ticketRepository.findById(id).orElseThrow(()->new TicketNotFoundException());
    }

    @Transactional(readOnly = true)
    public List<Ticket> getTicketsByUserId(Long userId) {
        return ticketRepository.findTicketsByUserId(userId);
    }

    @Transactional
    public Ticket updateTicketType(Long id, TicketType type) {
        Ticket ticket = ticketRepository.findById(id).orElseThrow(()->new TicketNotFoundException());
        ticket.setType(type);
        return ticketRepository.save(ticket);
    }

    @Transactional
    public void deleteTicket(Long id){
        Ticket ticket = ticketRepository.findById(id).orElseThrow(()->new TicketNotFoundException());
        ticketRepository.delete(ticket);
    }
}
