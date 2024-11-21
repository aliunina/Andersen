package org.andersen;

import org.andersen.connection.ConnectionFactory;
import org.andersen.dao.TicketDao;
import org.andersen.dao.UserDao;
import org.andersen.model.TicketType;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    private static final String USER = "postgres";
    private static final String PASSWORD = "pass123";

    public static void main(String[] args) {
        ConnectionFactory cf = new ConnectionFactory();
        Connection connection = cf.connectToDatabase(USER, PASSWORD);
        try {
            UserDao userDao = new UserDao();
            userDao.insertUser("Polina");

            TicketDao ticketDao = new TicketDao();
            ticketDao.insertTicket(4L, TicketType.WEEK);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
