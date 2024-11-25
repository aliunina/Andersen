package org.andersen.dao;

import org.andersen.model.Ticket;
import org.andersen.model.TicketType;
import org.hibernate.HibernateException;

import java.util.List;

public interface TicketDao {
    void insertTicket(Ticket ticket) throws HibernateException;

    Ticket selectTicketById(long id) throws HibernateException;

    List<Ticket> selectTicketsByUserId(long userId) throws HibernateException;

    void updateTicketType(long id, TicketType type) throws HibernateException;
}
