package com.epam.training.ticketservice.exception;

public class AlreadyExistsException extends OperationException {
    public AlreadyExistsException(String message) {
        super(message + " already exists");
    }
}
