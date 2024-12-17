package org.andersen.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class UserNotFoundException extends NoSuchElementException {
    public UserNotFoundException() {
        super("User isn't found.");
    }
}
