package org.andersen.dao;

import org.andersen.model.User;
import org.hibernate.HibernateException;

public interface UserDao {
    User selectUserById(long id) throws HibernateException;

    void insertUser(User user) throws HibernateException;

    void deleteUser(long id) throws HibernateException;

    void updateUser(User user) throws HibernateException;
}
