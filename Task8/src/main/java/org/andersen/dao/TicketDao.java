package org.andersen.dao;

import org.andersen.model.Ticket;
import org.andersen.model.TicketType;
import org.hibernate.SQLException;

import java.util.List;

public interface TicketDao {
    public void insertTicket(Ticket ticket) throws SQLException;

    public Ticket selectTicketById(long id) throws SQLException;

    public List<Ticket> selectTicketsByUserId(long userId) throws SQLException;

    public void updateTicketType(long id, TicketType type) throws SQLException;
}
