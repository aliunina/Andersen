package services;

import java.io.BufferedReader;

import org.json.*;
import validation.BusTicketValidator;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TicketService {
    public JSONArray readTicketsFromFile (String filePath) {
        JSONArray tickets = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder jsonStr = new StringBuilder();
            String curStr;
            while((curStr = reader.readLine()) != null) {
                jsonStr.append(curStr);
            }
            tickets = new JSONArray(jsonStr.toString());
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (IOException e) {
            System.out.println("File wouldn't load!");
        } finally {
            return tickets;
        }
    }

    public void validateTickets (JSONArray tickets) {
        BusTicketValidator validator = new BusTicketValidator();
        validator.validateTickets(tickets);
    }
}
