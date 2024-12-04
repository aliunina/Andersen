package org.andersen;

import org.andersen.service.TicketService;
import org.andersen.service.UserService;
import org.andersen.util.ApplicationContextConfigurationUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(ApplicationContextConfigurationUtil.class);
        String fileName = "tickets.json";

        try {
            UserService userService = ctx.getBean(UserService.class);
            TicketService ticketService = ctx.getBean(TicketService.class);

            userService.activateUser(1);

            System.out.println(ticketService.getTicketsFromFile(fileName));
        } catch (SQLException | IllegalAccessException | IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
