package org.andersen.dao.impl;

import org.andersen.dao.TicketDao;
import org.andersen.model.ticket.Ticket;
import org.andersen.model.ticket.TicketType;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class TicketDaoImpl implements TicketDao {
    private final DataSource dataSource;
    private static final String SQL_INSERT_TICKET =
            "INSERT INTO public.ticket(id, user_id, ticket_type, creation_date) VALUES (?, ?, ?, ?)";
    private static final String SQL_SELECT_TICKET_BY_ID = "SELECT * FROM public.ticket WHERE id = '%s'";
    private static final String SQL_SELECT_TICKETS_BY_USER_ID = "SELECT * FROM public.ticket WHERE user_id = '%s'";
    private static final String SQL_UPDATE_TICKET_TYPE = "UPDATE public.ticket SET ticket_type='%s' WHERE id = %d";

    public TicketDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Transactional
    @Override
    public void insertTicket(Ticket ticket) throws SQLException {
        PreparedStatement ps = dataSource.getConnection().prepareStatement(SQL_INSERT_TICKET);
        ps.setLong(1, ticket.getId());
        ps.setLong(2, ticket.getUserId());
        ps.setString(3, ticket.getType().name());
        ps.setTimestamp(4, ticket.getCreationDate());
        ps.executeUpdate();
        System.out.println("Ticket inserted.");
    }

    @Transactional(readOnly = true)
    @Override
    public Ticket selectTicketById(long id) throws SQLException {
        String query = String.format(SQL_SELECT_TICKET_BY_ID, id);
        Statement statement = dataSource.getConnection().createStatement();

        ResultSet res = statement.executeQuery(query);
        res.next();
        Ticket ticket = new Ticket(res.getLong("id"), res.getLong("user_id"),
                TicketType.valueOf(res.getString("ticket_type")),
                res.getTimestamp("creation_date"));
        return ticket;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Ticket> selectTicketsByUserId(long userId) throws SQLException {
        String query = String.format(SQL_SELECT_TICKETS_BY_USER_ID, userId);
        Statement statement = dataSource.getConnection().createStatement();

        ResultSet res = statement.executeQuery(query);
        List<Ticket> tickets = new ArrayList<>();
        while (res.next()) {
            tickets.add(new Ticket(res.getLong("id"), res.getLong("user_id"),
                    TicketType.valueOf(res.getString("ticket_type")),
                    res.getTimestamp("creation_date")));
        }
        return tickets;
    }

    @Transactional
    @Override
    public void updateTicketType(long id, TicketType type) throws SQLException {
        String query = String.format(SQL_UPDATE_TICKET_TYPE, type, id);
        Statement statement = dataSource.getConnection().createStatement();
        statement.executeUpdate(query);
        System.out.println("Ticket #" + id + " updated.");
    }
}
