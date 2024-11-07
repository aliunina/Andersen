package services;

import java.io.BufferedReader;

import org.json.*;
import validation.BusTicketValidator;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TicketService {
    public JSONArray readTicketsFromFile (String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder jsonStr = new StringBuilder();
            String curStr;
            while((curStr = reader.readLine()) != null) {
                jsonStr.append(curStr);
            }
            JSONArray tickets = new JSONArray(jsonStr.toString());
            return tickets;
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (IOException e) {
            System.out.println("File wouldn't load!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public void validateTickets (JSONArray tickets) {
        BusTicketValidator validator = new BusTicketValidator();
        validator.validateTickets(tickets);
    }
}
