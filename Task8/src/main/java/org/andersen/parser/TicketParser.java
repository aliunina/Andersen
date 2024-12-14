package org.andersen.parser;

import org.andersen.model.ticket.BusTicket;
import org.andersen.model.ticket.TicketType;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class TicketParser {
    @Value("${app.tickets.json.filename}")
    private String fileName;

    public List<BusTicket>  parseTickets() throws IOException {
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
