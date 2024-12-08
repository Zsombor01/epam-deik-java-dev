package com.epam.training.ticketservice.exception;

public class NotFoundException extends OperationException {
    public NotFoundException(String message) {
        super(message + " not found");
    }
}
