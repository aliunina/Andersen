package org.andersen.dao.impl;

import jakarta.persistence.Query;
import org.andersen.dao.TicketDao;
import org.andersen.model.Ticket;
import org.andersen.model.TicketType;
import org.andersen.util.HibernateSessionFactoryUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class TicketDaoImpl implements TicketDao {
    private final String HQL_GET_TICKETS_BY_USER_ID = "FROM Ticket t WHERE t.user.id = :userId";

    private final String HQL_UPDATE_TICKET_TYPE =
            "UPDATE Ticket t SET t.ticketType = :ticketType WHERE t.id = :ticketId";

    private SessionFactory sessionFactory;

    public TicketDaoImpl() {
        sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();
    }

    @Override
    public void insertTicket(Ticket ticket) throws HibernateException {
        Session session = sessionFactory.openSession();
        Transaction tx1 = session.beginTransaction();
        session.persist(ticket);
        tx1.commit();
        session.close();
    }

    @Override
    public Ticket selectTicketById(long id) throws HibernateException {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Ticket ticket = session.get(Ticket.class, id);
        session.getTransaction().commit();
        session.close();
        return ticket;
    }

    @Override
    public List<Ticket> selectTicketsByUserId(long userId) throws HibernateException {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery(HQL_GET_TICKETS_BY_USER_ID, Ticket.class);
        query.setParameter("userId", userId);
        List<Ticket> tickets = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return tickets;
    }

    @Override
    public void updateTicketType(long id, TicketType type) throws HibernateException {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.createMutationQuery(HQL_UPDATE_TICKET_TYPE)
                .setParameter("ticketType", type)
                .setParameter("ticketId", id)
                .executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
}
