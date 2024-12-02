package org.andersen;

import org.andersen.dao.TicketDao;
import org.andersen.dao.UserDao;
import org.andersen.model.*;
import org.andersen.util.ApplicationContextConfigurationUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(ApplicationContextConfigurationUtil.class);

        try {
            UserDao userDao = ctx.getBean(UserDao.class);
            TicketDao ticketDao = ctx.getBean(TicketDao.class);
            DataSource dataSource = ctx.getBean(DataSource.class);

            userDao.insertUser("new user");
            ticketDao.insertTicket(4, TicketType.WEEK);

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
