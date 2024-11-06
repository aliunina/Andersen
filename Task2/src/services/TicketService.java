package services;

import model.ticket.Ticket;

import java.io.BufferedReader;

import org.json.*;
import validation.BusTicketValidator;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TicketService {
    private TicketStorage storage;

    public TicketService() {
        storage = new TicketStorage();
    }

    public void readTicketsFromFile (String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder jsonStr = new StringBuilder();
            String curStr;
            while((curStr = reader.readLine()) != null){
                jsonStr.append(curStr);
            }
            JSONArray tickets = new JSONArray(jsonStr.toString());
            BusTicketValidator.validateTickets(tickets);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void validateTickets (ArrayList<Ticket> tickets) {

    }
}
