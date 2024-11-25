package org.andersen.dao.impl;

import org.andersen.connection.ConnectionFactory;
import org.andersen.dao.TicketDao;
import org.andersen.model.Ticket;
import org.andersen.model.TicketType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketDaoImpl implements TicketDao {
    Connection connection = ConnectionFactory.getConnection();
    private static final String SQL_INSERT_TICKET =
            "INSERT INTO public.\"Ticket\"(id, user_id, ticket_type, creation_date) VALUES (DEFAULT, %d, '%s', '%s')";
    private static final String SQL_SELECT_TICKET_BY_ID = "SELECT * FROM public.\"Ticket\" WHERE id = '%s'";
    private static final String SQL_SELECT_TICKETS_BY_USER_ID = "SELECT * FROM public.\"Ticket\" WHERE user_id = '%s'";
    private static final String SQL_UPDATE_TICKET_TYPE = "UPDATE public.\"Ticket\" SET ticket_type='%s' WHERE id = %d";

    @Override
    public void insertTicket(long userId, TicketType type) throws SQLException {
        String query = String.format(SQL_INSERT_TICKET, userId, type, getCreationDate());
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
        System.out.println("Ticket inserted.");
    }

    @Override
    public Ticket selectTicketById(long id) throws SQLException {
        String query = String.format(SQL_SELECT_TICKET_BY_ID, id);
        Statement statement = connection.createStatement();

        ResultSet res = statement.executeQuery(query);
        res.next();
        Ticket ticket = new Ticket(res.getLong("id"), res.getLong("user_id"),
                TicketType.valueOf(res.getString("ticket_type")), res.getDate("creation_date"));
        return ticket;
    }

    @Override
    public List<Ticket> selectTicketsByUserId(long userId) throws SQLException {
        String query = String.format(SQL_SELECT_TICKETS_BY_USER_ID, userId);
        Statement statement = connection.createStatement();

        ResultSet res = statement.executeQuery(query);
        List<Ticket> tickets = new ArrayList<>();
        while (res.next()) {
            tickets.add(new Ticket(res.getLong("id"), res.getLong("user_id"),
                    TicketType.valueOf(res.getString("ticket_type")),
                    res.getDate("creation_date")));
        }
        return tickets;
    }

    @Override
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
