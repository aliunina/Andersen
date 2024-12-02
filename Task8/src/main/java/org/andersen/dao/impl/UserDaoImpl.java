package org.andersen.dao.impl;

import org.andersen.dao.UserDao;
import org.andersen.model.User;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;

@Component
public class UserDaoImpl implements UserDao {
    private final DataSource dataSource;
    private static final String SQL_INSERT_USER =
            "INSERT INTO public.\"User\"(id, name, creation_date) VALUES (?, ?, ?)";
    private static final String SQL_SELECT_USER_BY_ID = "SELECT * FROM public.\"User\" WHERE id = %d";
    private static final String SQL_DELETE_USER_TICKETS = "DELETE FROM public.\"Ticket\" WHERE user_id = %d";
    private static final String SQL_DELETE_USER = "DELETE FROM public.\"User\" WHERE id = %d";

    public UserDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void insertUser(User user) throws SQLException {
        PreparedStatement ps = dataSource.getConnection().prepareStatement(SQL_INSERT_USER);
        ps.setLong(1, user.getId());
        ps.setString(2, user.getName());
        ps.setTimestamp(3, user.getCreationDate());
        ps.executeUpdate();
        System.out.println("User inserted.");
    }

    @Override
    public User selectUserById(long id) throws SQLException {
        String query = String.format(SQL_SELECT_USER_BY_ID, id);
        Statement statement = dataSource.getConnection().createStatement();

        ResultSet res = statement.executeQuery(query);
        res.next();
        User user = new User(res.getLong("id"), res.getString("name"),
                res.getTimestamp("creation_date"));
        return user;
    }

    @Override
    public void deleteUser(long id) throws SQLException {
        Statement statement = dataSource.getConnection().createStatement();
        String query1 = String.format(SQL_DELETE_USER_TICKETS, id);
        statement.executeUpdate(query1);
        String query2 = String.format(SQL_DELETE_USER, id);
        int res = statement.executeUpdate(query2);
        if (res > 0) {
            System.out.println("User deleted.");
        } else {
            System.out.println("No users deleted.");
        }
    }

    private Date getCreationDate() {
        return new Date(System.currentTimeMillis());
    }
}
