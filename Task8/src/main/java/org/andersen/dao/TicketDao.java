package org.andersen.dao;

import org.andersen.connection.ConnectionFactory;
import org.andersen.model.TicketType;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;

public class TicketDao {
    private static final String SQL_INSERT_TICKET =
            "INSERT INTO public.\"Ticket\"(id, user_id, ticket_type, creation_date) VALUES (DEFAULT, %d, '%s', '%s')";

    public void insertTicket(Long userId, TicketType type) throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        String query = String.format(SQL_INSERT_TICKET, userId, type, getCreationDate());
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
        System.out.println("Ticket inserted.");
    }

    private Date getCreationDate() {
        return new Date(System.currentTimeMillis());
    }
}
