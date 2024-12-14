package org.andersen.exception;

public class EmptyUserNameException extends RuntimeException {
    public EmptyUserNameException() {
        super("The 'name' field is required and cannot be empty.");
    }
}