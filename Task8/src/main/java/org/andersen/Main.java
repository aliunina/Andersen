package org.andersen;

import org.andersen.dao.impl.TicketDaoImpl;
import org.andersen.dao.impl.UserDaoImpl;
import org.andersen.model.Ticket;
import org.andersen.model.TicketType;
import org.andersen.model.User;
=======
import org.andersen.connection.ConnectionFactory;
import org.andersen.dao.TicketDao;
import org.andersen.dao.UserDao;
import org.andersen.dao.impl.TicketDaoImpl;
import org.andersen.dao.impl.UserDaoImpl;
import org.andersen.model.*;

import java.sql.SQLException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        try {
            UserDaoImpl userDao = new UserDaoImpl();
            TicketDaoImpl ticketDao = new TicketDaoImpl();
           User user = userDao.selectUserById(4);
           System.out.println(user);

           user.setName("USER");
           user.getTickets().get(0).setTicketType(TicketType.MONTH);

           userDao.updateUser(user);

           User newUser = new User("Helen");
           userDao.insertUser(newUser);

           System.out.println(ticketDao.selectTicketById(5));

           ticketDao.updateTicketType(4, TicketType.YEAR);

           ticketDao.insertTicket(new Ticket(user, TicketType.MONTH));
           ticketDao.insertTicket(new Ticket(user, TicketType.YEAR));

           System.out.println(ticketDao.selectTicketsByUserId(3));

           userDao.deleteUser(3);
        } catch (Exception e) {
            UserDao userDao = new UserDaoImpl();
            TicketDao ticketDao = new TicketDaoImpl();

            userDao.insertUser(new User(1, "Peter"));
            ticketDao.insertTicket(new Ticket(1, 4, TicketType.WEEK));

            User user = userDao.selectUserById(4);
            System.out.println("Selected user: " + user);
            Ticket ticket = ticketDao.selectTicketById(4);
            System.out.println("Selected ticket: " + ticket);

            List<Ticket> tickets = ticketDao.selectTicketsByUserId(4);
            System.out.println(tickets);

            ticketDao.updateTicketType(4, TicketType.YEAR);
            userDao.deleteUser(3);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
