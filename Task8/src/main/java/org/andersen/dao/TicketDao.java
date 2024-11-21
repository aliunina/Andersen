package org.andersen.dao;

import org.andersen.connection.ConnectionFactory;
import org.andersen.model.Ticket;
import org.andersen.model.TicketType;
import org.andersen.model.User;

import java.sql.*;

public class TicketDao {
    Connection connection = ConnectionFactory.getConnection();
    private static final String SQL_INSERT_TICKET =
            "INSERT INTO public.\"Ticket\"(id, user_id, ticket_type, creation_date) VALUES (DEFAULT, %d, '%s', '%s')";
    private static final String SQL_SELECT_TICKET_BY_ID =
            "SELECT * FROM public.\"Ticket\" WHERE id = '%s' AND user_id = '%s'";
    private static final String SQL_UPDATE_TICKET_TYPE = "UPDATE public.\"Ticket\" SET ticket_type='%s' WHERE id = %d";

    public void insertTicket(long userId, TicketType type) throws SQLException {
        String query = String.format(SQL_INSERT_TICKET, userId, type, getCreationDate());
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
        System.out.println("Ticket inserted.");
    }

    public Ticket selectTicketById(long id, long userId) throws SQLException {
        String query = String.format(SQL_SELECT_TICKET_BY_ID, id, userId);
        Statement statement = connection.createStatement();

        ResultSet res = statement.executeQuery(query);
        res.next();
        Ticket ticket = new Ticket(res.getLong("id"), res.getLong("user_id"),
                TicketType.valueOf(res.getString("ticket_type")), res.getDate("creation_date"));
        return ticket;
    }

    public void updateTicketType(long id, TicketType type) throws SQLException {
        String query = String.format(SQL_UPDATE_TICKET_TYPE, type, id);
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
        System.out.println("Ticket #" + id + " updated.");
    }

    private Date getCreationDate() {
        return new Date(System.currentTimeMillis());
    }
}
