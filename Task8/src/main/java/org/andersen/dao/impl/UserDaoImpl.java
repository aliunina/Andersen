package org.andersen.dao.impl;

import org.andersen.dao.UserDao;
import org.andersen.model.ticket.Ticket;
import org.andersen.model.ticket.TicketType;
import org.andersen.model.user.User;
import org.andersen.model.user.UserStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.*;

@Component
public class UserDaoImpl implements UserDao {
    @Value("${app.activate-user.enabled}")
    private boolean activationEnabled;
    private final DataSource dataSource;
    private static final String SQL_INSERT_USER =
            "INSERT INTO public.user(id, name, creation_date, status) VALUES (?, ?, ?, ?)";
    private static final String SQL_SELECT_USER_BY_ID = "SELECT * FROM public.user WHERE id = %d";
    private static final String SQL_DELETE_USER_TICKETS = "DELETE FROM public.ticket WHERE user_id = %d";
    private static final String SQL_DELETE_USER = "DELETE FROM public.user WHERE id = %d";
    private static final String SQL_ACTIVATE_USER =
            "UPDATE public.user SET status = cast(? as status_type) WHERE id = ?";
    private static final String SQL_INSERT_TICKET_FOR_ACTIVATED_USER =
            "INSERT INTO ticket (id, user_id, ticket_type, creation_date) " +
                    "VALUES (nextval('ticket_id_seq'), ?, ?, ?)";

    public UserDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Transactional
    @Override
    public void insertUser(User user) throws SQLException {
        PreparedStatement ps = dataSource.getConnection().prepareStatement(SQL_INSERT_USER);
        ps.setLong(1, user.getId());
        ps.setString(2, user.getName());
        ps.setTimestamp(3, user.getCreationDate());
        ps.setString(4, String.valueOf(user.getStatus()));
        ps.executeUpdate();
        System.out.println("User inserted.");
    }

    @Transactional
    @Override
    public User selectUserById(long id) throws SQLException {
        String query = String.format(SQL_SELECT_USER_BY_ID, id);
        Statement statement = dataSource.getConnection().createStatement();

        ResultSet res = statement.executeQuery(query);
        res.next();
        User user = new User(res.getLong("id"), res.getString("name"),
                res.getTimestamp("creation_date"), UserStatus.valueOf(res.getString("status")));
        return user;
    }

    @Transactional
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

    @Transactional
    @Override
    public void activateUser(long id) throws IllegalAccessException, SQLException {
        if (!activationEnabled) {
            throw new IllegalAccessException("User activation is disabled!");
        }
        Ticket ticket = new Ticket(id, TicketType.DAY);

        PreparedStatement ps1 = dataSource.getConnection().prepareStatement(SQL_ACTIVATE_USER);
        ps1.setString(1, UserStatus.ACTIVATED.name());
        ps1.setLong(2, id);
        ps1.executeUpdate();

        PreparedStatement ps2 = dataSource.getConnection().prepareStatement(SQL_INSERT_TICKET_FOR_ACTIVATED_USER);
        ps2.setLong(1, ticket.getUserId());
        ps2.setString(2, ticket.getType().name());
        ps2.setTimestamp(3, ticket.getCreationDate());
        ps2.executeUpdate();
    }
}
