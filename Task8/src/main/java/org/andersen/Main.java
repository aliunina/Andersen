package org.andersen;

import org.andersen.dao.impl.TicketDaoImpl;
import org.andersen.dao.impl.UserDaoImpl;
import org.andersen.model.Ticket;
import org.andersen.model.TicketType;
import org.andersen.model.User;

public class Main {

    public static void main(String[] args) {
        try {
            UserDaoImpl userDao = new UserDaoImpl();
            TicketDaoImpl ticketDao = new TicketDaoImpl();
//            User user = userDao.selectUserById(4);
//            System.out.println(user);
//
//            user.setName("USER");
//            user.getTickets().get(0).setTicketType(TicketType.MONTH);
//
//            userDao.updateUser(user);

            User newUser = new User("Helen");
            userDao.insertUser(newUser);

//            System.out.println(ticketDao.selectTicketById(5));
//
//            ticketDao.updateTicketType(4, TicketType.YEAR);
//
//            ticketDao.insertTicket(new Ticket(user, TicketType.MONTH));
//            ticketDao.insertTicket(new Ticket(user, TicketType.YEAR));
//
//            System.out.println(ticketDao.selectTicketsByUserId(3));
//
//            userDao.deleteUser(3);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
