package org.andersen.controller;

import org.andersen.exception.UserActivationIsDisabledException;
import org.andersen.model.ticket.Ticket;
import org.andersen.model.ticket.TicketType;
import org.andersen.model.user.User;
import org.andersen.service.TicketService;
import org.andersen.service.UserService;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping ("/api/")
@RequiredArgsConstructor
public class AppController {
    private final UserService userService;

    private final TicketService ticketService;

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable ("id") Long id){
        return userService.getUserById(id);
    }

    @GetMapping("/user/with_tickets/{id}")
    public User getUserWithTickets(@PathVariable ("id") Long id){
        return userService.getUserWithTicketsById(id);
    }

    @PostMapping("/user/new")
    public User createUser(@RequestBody User userDetails) {
        return userService.addUser(userDetails);
    }

    @PutMapping("/user/{id}/activate")
    public void activateUser(@PathVariable Long id) throws UserActivationIsDisabledException {
        userService.activateUser(id);
    }

    @DeleteMapping("/user/{id}/delete")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }


    @GetMapping("/ticket/{id}")
    public Ticket getTicket(@PathVariable ("id") Long id){
        return ticketService.getTicketById(id);
    }

    @GetMapping("/user/tickets/{userId}")
    public List<Ticket> getTicketsByUserId(@PathVariable Long userId) {
        return ticketService.getTicketsByUserId(userId);
    }

    @PostMapping("/ticket/new")
    public Ticket createTicket(@RequestBody Ticket ticket) {
        return ticketService.addTicket(ticket);
    }

    @PutMapping("/ticket/{id}/update_type/{type}")
    public void updateTicketType(@PathVariable Long id, @PathVariable TicketType type) {
        ticketService.updateTicketType(id, type);
    }

    @DeleteMapping("/ticket/{id}/delete")
    public void deleteTicket(@PathVariable Long id) {
        ticketService.deleteTicket(id);
    }
}
