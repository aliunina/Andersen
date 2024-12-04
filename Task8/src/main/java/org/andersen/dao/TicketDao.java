package org.andersen.dao;

import org.andersen.model.ticket.Ticket;
import org.andersen.model.ticket.TicketType;

import java.sql.SQLException;
import java.util.List;

public interface TicketDao {
    public void insertTicket(Ticket ticket) throws SQLException;

    public Ticket selectTicketById(long id) throws SQLException;

    public List<Ticket> selectTicketsByUserId(long userId) throws SQLException;

    public void updateTicketType(long id, TicketType type) throws SQLException;
}
