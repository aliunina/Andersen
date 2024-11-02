package user;

import model.Ticket;

import java.sql.Timestamp;

public class Client implements User {

    @Override
    public void printRole() {
        System.out.println("Your role is Client");
    }

    public void getTicket(Ticket ticket) {
        System.out.println("This client has ticket #" + ticket.getId());
    }
}
