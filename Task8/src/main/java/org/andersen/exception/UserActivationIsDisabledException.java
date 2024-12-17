package org.andersen.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.FORBIDDEN)
public class UserActivationIsDisabledException extends IllegalAccessException {
    public UserActivationIsDisabledException() {
        super("Activation is disabled.");
    }
}
