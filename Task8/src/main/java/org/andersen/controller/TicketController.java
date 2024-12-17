package org.andersen.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
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
    @ApiOperation(value = "Get ticket")
    @ApiResponse(code = 200, message = "OK")
    public Ticket getTicket(@PathVariable ("id") Long id){
        return ticketService.getTicketById(id);
    }

    @GetMapping("/by_user/{userId}")
    @ApiOperation(value = "Get ticket by userId")
    @ApiResponse(code = 200, message = "OK")
    public List<Ticket> getTicketsByUserId(@PathVariable Long userId) {
        return ticketService.getTicketsByUserId(userId);
    }

    @PostMapping("/new")
    @ApiOperation(value = "Create ticket")
    @ApiResponse(code = 201, message = "Created")
    public Ticket createTicket(@RequestBody Ticket ticket) {
        return ticketService.addTicket(ticket);
    }

    @PutMapping("/{id}/update_type/{type}")
    @ApiOperation(value = "Update ticket type")
    @ApiResponse(code = 200, message = "OK")
    public void updateTicketType(@PathVariable Long id, @PathVariable TicketType type) {
        ticketService.updateTicketType(id, type);
    }

    @DeleteMapping("/{id}/delete")
    @ApiOperation(value = "Delete ticket")
    @ApiResponse(code = 200, message = "OK")
    public void deleteTicket(@PathVariable Long id) {
        ticketService.deleteTicket(id);
    }
}
