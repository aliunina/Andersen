package org.andersen;

import org.andersen.connection.ConnectionFactory;
import org.andersen.dao.TicketDao;
import org.andersen.dao.UserDao;
import org.andersen.dao.impl.TicketDaoImpl;
import org.andersen.dao.impl.UserDaoImpl;
import org.andersen.model.*;

import java.sql.SQLException;
import java.util.List;

public class Main {
    private static final String USER = "postgres";
    private static final String PASSWORD = "pass123";

    public static void main(String[] args) {
        ConnectionFactory cf = new ConnectionFactory();
        cf.connectToDatabase(USER, PASSWORD);
        try {
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
