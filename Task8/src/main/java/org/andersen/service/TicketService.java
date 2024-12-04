package org.andersen.service;

import org.andersen.dao.TicketDao;
import org.andersen.model.ticket.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.json.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class TicketService {
    private final TicketDao ticketDao;

    public TicketService(TicketDao ticketDao) {
        this.ticketDao = ticketDao;
    }

    public void addTicket(Ticket ticket) throws SQLException {
        ticketDao.insertTicket(ticket);
    }

    public Ticket getTicketById(long id) throws SQLException {
        return ticketDao.selectTicketById(id);
    }

    public List<Ticket> getTicketsByUserId(long userId) throws SQLException {
        return ticketDao.selectTicketsByUserId(userId);
    }

    public void updateTicketType(long id, TicketType type) throws SQLException {
        ticketDao.updateTicketType(id, type);
    }

    public List<BusTicket> getTicketsFromFile(String fileName) throws IOException {
        List<BusTicket> result = new ArrayList<>();
        Resource resource = new ClassPathResource(fileName);
        BufferedReader reader = new BufferedReader(new FileReader(resource.getFile()));
        StringBuilder jsonStr = new StringBuilder();
        String curStr;

        while((curStr = reader.readLine()) != null) {
            jsonStr.append(curStr);
        }

        JSONArray tickets = new JSONArray(jsonStr.toString());

        for (int i = 0; i < tickets.length(); i++) {
            JSONObject ticket = tickets.getJSONObject(i);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate startDate = null;
            if (!ticket.isNull("startDate")) {
                startDate = LocalDate.parse(ticket.getString("startDate"), formatter);
            }

            result.add(
                    new BusTicket(ticket.getString("ticketClass"),
                    TicketType.valueOf(ticket.getString("ticketType")),
                    startDate,
                    new BigDecimal(ticket.getFloat("price"))));
        }

        reader.close();

        return result;
    }
}
