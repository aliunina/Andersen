package org.andersen;

import org.andersen.connection.ConnectionFactory;
import org.andersen.dao.TicketDao;
import org.andersen.dao.UserDao;
import org.andersen.model.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;

public class Main {
    private static final String USER = "postgres";
    private static final String PASSWORD = "pass123";

    public static void main(String[] args) {
        ConnectionFactory cf = new ConnectionFactory();
        Connection connection = cf.connectToDatabase(USER, PASSWORD);
        try {
            UserDao userDao = new UserDao();
            TicketDao ticketDao = new TicketDao();

//            userDao.insertUser("new user");
//            ticketDao.insertTicket(4L, TicketType.WEEK);

//            User user = userDao.selectUserById(4L);
//            System.out.println("Selected user: " + user);
//            Ticket ticket = ticketDao.selectTicketById(5L, 4L);
//            System.out.println("Selected ticket: " + ticket);

            ticketDao.updateTicketType(3, TicketType.MONTH);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
