package org.andersen.dao;

import org.andersen.model.User;

import java.sql.SQLException;

public interface UserDao {
    public void insertUser(User user) throws SQLException;

    public User selectUserById(long id) throws SQLException;

    public void deleteUser(long id) throws SQLException;
}
