package model.user;

import model.Identifier;
import model.ticket.Ticket;

public class Client extends Identifier implements Printable {

    @Override
    public void printRole() {
        System.out.println("Your role is Client.");
    }

    public void getTicket(Ticket ticket) {
        System.out.println("This client has ticket #" + ticket.getId() + ".");
    }
}
