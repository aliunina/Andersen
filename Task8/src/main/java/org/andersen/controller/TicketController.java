package org.andersen.controller;

import lombok.RequiredArgsConstructor;
import org.andersen.model.ticket.Ticket;
import org.andersen.model.ticket.TicketType;
import org.andersen.service.TicketService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ticket/")
@RequiredArgsConstructor
public class TicketController {
    private final TicketService ticketService;

    @GetMapping("/{id}")
    public Ticket getTicket(@PathVariable ("id") Long id){
        return ticketService.getTicketById(id);
    }

    @GetMapping("/by_user/{userId}")
    public List<Ticket> getTicketsByUserId(@PathVariable Long userId) {
        return ticketService.getTicketsByUserId(userId);
    }

    @PostMapping("/new")
    public Ticket createTicket(@RequestBody Ticket ticket) {
        return ticketService.addTicket(ticket);
    }

    @PutMapping("/{id}/update_type/{type}")
    public void updateTicketType(@PathVariable Long id, @PathVariable TicketType type) {
        ticketService.updateTicketType(id, type);
    }

    @DeleteMapping("/{id}/delete")
    public void deleteTicket(@PathVariable Long id) {
        ticketService.deleteTicket(id);
    }
}
