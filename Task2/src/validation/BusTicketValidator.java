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
    private StringBuilder errorMessage;
    private String ticketType = null;

    public void validateTickets(JSONArray tickets) {
        errorMessage = new StringBuilder("");
        boolean valid = true;

        try {
            for (int i = 0; i < tickets.length(); i++) {
                    JSONObject ticket = tickets.getJSONObject(i);

                    valid = validateClass(ticket, i) && valid;
                    valid = validateType(ticket, i) && valid;
                    valid = validateDate(ticket, i) && valid;
                    valid = validatePrice(ticket, i) && valid;

                    if (valid) totalValid++;
                    valid = true;
            }
            printStats(tickets.length());
        } catch (JSONException e) {
            System.out.println("Incorrect JSON format!");
            System.out.println(e.getMessage());
        } catch (DateTimeParseException e) {
            System.out.println("[startDate] format is incorrect!");
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    private boolean validateClass(JSONObject ticket, int index) {
        boolean valid = true;
        if (ticket.isNull("ticketClass")) {
            errorMessage.append("\nTicket #" + index + ": [ticketClass] can't be null");
            classViolated++;
            valid = false;
        }
        return valid;
    }

    private boolean validateType(JSONObject ticket, int index) throws Exception {
        boolean valid = true;

        if (ticket.isNull("ticketType")) ticketType = "";
        else ticketType = String.valueOf(ticket.get("ticketType"));

        try {
            TicketType.valueOf(ticketType);
        } catch (IllegalArgumentException e) {
            errorMessage.append("\nTicket #" + index + ": [ticketType] valid values are DAY, WEEK, MONTH, YEAR");
            typeViolated++;
            valid = false;
        }

        return valid;
    }

    private boolean validateDate(JSONObject ticket, int index) throws DateTimeParseException {
        LocalDate startDate;
        boolean valid = true;
        boolean dateRequiringType = ticketType.equals(TicketType.DAY.name())
                || ticketType.equals(TicketType.WEEK.name()) || ticketType.equals(TicketType.YEAR.name());

        if (dateRequiringType) {
            if ((ticket.isNull("startDate") || ticket.getString("startDate").equals(""))) {
                errorMessage.append("\nTicket #" + index + ": Only DAY, WEEK and YEAR types must have a [startDate]");
                valid = false;
                dateViolated++;
            } else {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                startDate = LocalDate.parse(ticket.getString("startDate"), formatter);
                if (startDate.isAfter(LocalDate.now())) {
                    errorMessage.append("\nTicket #" + index + ": [startDate] can't be in future");
                    valid = false;
                    dateViolated++;
                }
            }
        } else {
            if (!ticket.isNull("startDate")) {
                errorMessage.append("\nTicket #" + index + ": Only DAY, WEEK and YEAR types must have a [startDate]");
                valid = false;
                dateViolated++;
            }
        }
        return valid;
    }

    private boolean validatePrice(JSONObject ticket, int index) throws Exception {
        boolean valid = true;
        if (ticket.isNull("price") || ticket.getFloat("price") == 0) {
            errorMessage.append("\nTicket #" + index + ": [price] can't be null or be equal to zero");
            valid = false;
            priceViolated++;
        } else if (ticket.getFloat("price") % 2 != 0) {
            errorMessage.append("\nTicket #" + index + ": [price] should always be even");
            valid = false;
            priceViolated++;
        }
        return valid;
    }

    private void printStats(int total) {
        StringBuilder popularViolation = new StringBuilder("");
        int maxViolations = Math.max(classViolated, Math.max(typeViolated, Math.max(dateViolated, priceViolated)));
        if (maxViolations == 0) {
            popularViolation.append("no violations");
        } else {
            if (classViolated == maxViolations) popularViolation.append("[ticketClass] violated " + classViolated + " times ");
            if (typeViolated == maxViolations) popularViolation.append("[ticketType] violated " + typeViolated + " times ");
            if (dateViolated == maxViolations) popularViolation.append("[startDate] violated " + dateViolated + " times ");
            if (priceViolated == maxViolations) popularViolation.append("[price] violated " + priceViolated + " times ");
        }

        if (classViolated + priceViolated + typeViolated + dateViolated > 1) {
            System.out.println(errorMessage);
        }
        System.out.printf("Total = %d\nValid = %d\nMost popular violation = %s\n",
                total, totalValid, popularViolation);
    }
}