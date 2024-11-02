package user;

import model.Ticket;
import model.TicketStorage;

import javax.management.InstanceNotFoundException;

public class Admin implements User{
    @Override
    public void printRole() {
        System.out.println("Your role is Admin");
    }

    public void checkTicket(TicketStorage ticketStorage, Ticket ticket) {
        try {
            ticketStorage.getTicketById(ticket.getId());
            System.out.println("Ticket has been found.");
        } catch (InstanceNotFoundException e){
            System.out.println("Ticket was not found.");
        }
    }
}
