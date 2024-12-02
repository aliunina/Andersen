package model.ticket;

public enum TicketType {
    DAY("DAY"),
    WEEK("WEEK"),
    YEAR("YEAR"),
    MONTH("MONTH");
    private final String name;
    TicketType(String s) {
        name = s;
    }
}
