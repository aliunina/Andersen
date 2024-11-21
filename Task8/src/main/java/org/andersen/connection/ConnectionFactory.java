package org.andersen.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionFactory {
    private static Connection connection;
    private final String DB_DRIVER = "org.postgresql.Driver";
    private final String CONNECTION_NAME = "jdbc:postgresql://localhost:5432/my_ticket_service_db";

    public Connection connectToDatabase(String user, String password) {
        connection = null;
        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(CONNECTION_NAME, user, password);
            if (connection != null) {
                System.out.println("Connection established.");
            } else {
                throw new SQLException();
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Can't find db driver class.");
        } catch (SQLException e) {
            System.out.println("Error occurred while establishing connection.");
        } finally {
            return connection;
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
