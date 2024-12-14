package org.andersen.service;

import org.andersen.dao.TicketDao;
import org.andersen.model.ticket.*;
import org.springframework.stereotype.Service;
import java.sql.SQLException;
import java.util.List;

@Service
public class TicketService {
    private final TicketDao ticketDao;

    public TicketService(TicketDao ticketDao) {
        this.ticketDao = ticketDao;
    }

    public void addTicket(Ticket ticket) throws SQLException {
        ticketDao.insertTicket(ticket);
    }

    public Ticket getTicketById(long id) throws SQLException {
        return ticketDao.selectTicketById(id);
    }

    public List<Ticket> getTicketsByUserId(long userId) throws SQLException {
        return ticketDao.selectTicketsByUserId(userId);
    }

    public void updateTicketType(long id, TicketType type) throws SQLException {
        ticketDao.updateTicketType(id, type);
    }
}
