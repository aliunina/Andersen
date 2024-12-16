package org.andersen.service;

import org.andersen.dao.UserDao;
import org.andersen.model.user.User;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void addUser(User user) throws SQLException {
        userDao.insertUser(user);
    }

    public User getUserById(long id) throws SQLException {
        return userDao.selectUserById(id);
    }

    public void deleteUser(long id) throws SQLException {
        userDao.deleteUser(id);
    }

    public void activateUser(long id) throws IllegalAccessException, SQLException {
        userDao.activateUser(id);
    }
}
