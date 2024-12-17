package org.andersen.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class TicketNotFoundException extends NoSuchElementException {
    public TicketNotFoundException() {
        super("Ticket isn't found.");
    }
}
