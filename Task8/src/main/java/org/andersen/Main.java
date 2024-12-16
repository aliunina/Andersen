package org.andersen;

import org.andersen.parser.TicketParser;
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

        try {
            UserService userService = ctx.getBean(UserService.class);
            TicketParser ticketParser = ctx.getBean(TicketParser.class);

            userService.activateUser(1);

            System.out.println(ticketParser.parseTickets());
        } catch (SQLException | IllegalAccessException | IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
