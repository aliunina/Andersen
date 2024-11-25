package org.andersen.dao.impl;

import org.andersen.dao.UserDao;
import org.andersen.model.User;
import org.andersen.util.HibernateSessionFactoryUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class UserDaoImpl implements UserDao {

    private SessionFactory sessionFactory;

    public UserDaoImpl() {
        sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();
    }

    @Override
    public void insertUser(User user) throws HibernateException {
        Session session = sessionFactory.openSession();
        Transaction tx1 = session.beginTransaction();
        session.persist(user);
        tx1.commit();
        session.close();
    }

    @Override
    public User selectUserById(long id) throws HibernateException {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        User user = session.get(User.class, id);
        session.getTransaction().commit();
        session.close();
        return user;
    }

    @Override
    public void deleteUser(long id) throws HibernateException {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        User user = session.get(User.class, id);
        session.remove(user);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updateUser(User user) throws HibernateException {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.merge(user);
        session.getTransaction().commit();
        session.close();
    }
}
