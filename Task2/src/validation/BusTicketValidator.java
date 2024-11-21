package validation;

import model.ticket.TicketType;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class BusTicketValidator {
    private int totalValid = 0, classViolated = 0, priceViolated = 0, typeViolated = 0, dateViolated = 0;
    private String ticketType = null;
    private JSONObject ticket;

    public void validateTickets(JSONArray tickets) {
        try {
            boolean valid = true;
            for (int i = 0; i < tickets.length(); i++) {
                ticket = tickets.getJSONObject(i);

                valid = validateClass() && valid;
                valid = validateType() && valid;
                valid = validateDate() && valid;
                valid = validatePrice() && valid;

                if (valid) {
                    totalValid++;
                }
                valid = true;
            }
            printStats(tickets.length());
        } catch (JSONException e) {
            System.out.println("Incorrect JSON format!");
            System.out.println(e.getMessage());
        } catch (DateTimeParseException e) {
            System.out.println("[startDate] format is incorrect!");
            System.out.println(e.getMessage());
        }
    }

    private boolean validateClass() {
        boolean valid = true;
        if (ticket.isNull("ticketClass")) {
            classViolated++;
            valid = false;
        }
        return valid;
    }

    private boolean validateType() {
        boolean valid = true;

        if (ticket.isNull("ticketType")) ticketType = "";
        else ticketType = String.valueOf(ticket.get("ticketType"));

        try {
            TicketType.valueOf(ticketType);
        } catch (IllegalArgumentException e) {
            typeViolated++;
            valid = false;
        }

        return valid;
    }

    private boolean validateDate() throws DateTimeParseException {
        LocalDate startDate;
        boolean valid = true;
        boolean dateRequiringType = ticketType.equals(TicketType.DAY.name())
                || ticketType.equals(TicketType.WEEK.name()) || ticketType.equals(TicketType.YEAR.name());

        if (dateRequiringType) {
            if ((ticket.isNull("startDate") || ticket.getString("startDate").equals(""))) {
                valid = false;
                dateViolated++;
            } else {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                startDate = LocalDate.parse(ticket.getString("startDate"), formatter);
                if (startDate.isAfter(LocalDate.now())) {
                    valid = false;
                    dateViolated++;
                }
            }
        } else {
            if (!ticket.isNull("startDate")) {
                valid = false;
                dateViolated++;
            }
        }
        return valid;
    }

    private boolean validatePrice() {
        boolean valid = true;
        if (ticket.isNull("price") || ticket.getFloat("price") == 0) {
            valid = false;
            priceViolated++;
        } else if (ticket.getFloat("price") % 2 != 0) {
            valid = false;
            priceViolated++;
        }
        return valid;
    }

    private void printStats(int total) {
        StringBuilder popularViolation = getMostPopularViolation();
        System.out.printf("Total = %d\nValid = %d\nMost popular violation = %s\n", total, totalValid, popularViolation);
    }

    private StringBuilder getMostPopularViolation() {
        StringBuilder popularViolation = new StringBuilder("");

        int maxViolations = Math.max(classViolated, Math.max(typeViolated, Math.max(dateViolated, priceViolated)));
        if (maxViolations == 0) {
            popularViolation.append("no violations");
        } else {
            if (classViolated == maxViolations)
                popularViolation.append("[ticketClass] violated " + classViolated + " times ");
            if (typeViolated == maxViolations)
                popularViolation.append("[ticketType] violated " + typeViolated + " times ");
            if (dateViolated == maxViolations)
                popularViolation.append("[startDate] violated " + dateViolated + " times ");
            if (priceViolated == maxViolations)
                popularViolation.append("[price] violated " + priceViolated + " times ");
        }

        return popularViolation;
    }
}