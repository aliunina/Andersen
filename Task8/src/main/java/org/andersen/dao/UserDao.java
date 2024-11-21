package org.andersen.dao;

import org.andersen.connection.ConnectionFactory;
import org.andersen.model.TicketType;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDao {
    private static final String SQL_INSERT_USER =
            "INSERT INTO public.\"User\"(id, name, creation_date) VALUES (DEFAULT, '%s', '%s')";

    public void insertUser(String name) throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        String query = String.format(SQL_INSERT_USER, name, getCreationDate());
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
        System.out.println("User inserted.");
    }

    private Date getCreationDate() {
        return new Date(System.currentTimeMillis());
    }
}
