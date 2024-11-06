package validation;

import model.ticket.TicketType;
import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BusTicketValidator {
    public static void validateTickets(JSONArray tickets) {
        int totalValid = 0, classViolated = 0, priceViolated = 0, typeViolated = 0, dateViolated = 0;
        StringBuilder errorMessage = new StringBuilder("");
        boolean valid = true;

        for (int i = 0; i < tickets.length(); i++)
        {
            JSONObject ticket = tickets.getJSONObject(i);
            LocalDate startDate;
            String ticketType = null;
            if (ticket.isNull("ticketClass")) {
                errorMessage.append("\n[ticketClass] can't be null");
                classViolated++;
                valid = false;
            }

            if (ticket.isNull("ticketType")) {
                errorMessage.append("\n[ticketType] can't be null");
                ticketType = "";
                typeViolated++;
                valid = false;
            } else {
                ticketType = ticket.getString("ticketType");
            }

            boolean dateRequiringType = ticketType.equals(TicketType.DAY.name())
                    || ticketType.equals(TicketType.WEEK.name()) || ticketType.equals(TicketType.YEAR.name());

            if ((dateRequiringType && (ticket.isNull("startDate") || ticket.getString("startDate").equals("")))
                    || (!dateRequiringType && !ticket.isNull("startDate"))) {
                errorMessage.append("\nOnly DAY, WEEK and YEAR types must have a [startDate]");
                valid = false;
                dateViolated++;
            }

            if (ticket.isNull("price") || ticket.getFloat("price") == 0) {
                errorMessage.append("\n[price] can't be null or be equal to zero");
                valid = false;
                priceViolated++;
            }
            if (valid) totalValid++;
            valid = true;
        }

        printStats(tickets.length(), totalValid, classViolated, priceViolated, typeViolated, dateViolated,
                errorMessage);
    }

    private static void printStats(int total, int totalValid, int classViolated, int priceViolated,
                                   int typeViolated, int dateViolated, StringBuilder errorMessage) {
        StringBuilder popularViolation = new StringBuilder("");
        int maxViolations = Math.max(classViolated, Math.max(typeViolated, Math.max(dateViolated, priceViolated)));
        if (classViolated == maxViolations) popularViolation.append("[ticketClass] violated " + classViolated + " times ");
        if (typeViolated == maxViolations) popularViolation.append("[ticketType] violated " + typeViolated + " times ");
        if (dateViolated == maxViolations) popularViolation.append("[startDate] violated " + dateViolated + " times ");
        if (priceViolated == maxViolations) popularViolation.append("[price] violated " + priceViolated + " times ");

        if (classViolated + priceViolated + typeViolated + dateViolated > 1) {
            System.out.println(errorMessage);
        }
        System.out.printf("Total = %d\nValid = %d\nMost popular violation = %s\n",
                total, totalValid, popularViolation);
    }
}