package com.epam.training.ticketservice.exception;

public class OperationException extends Exception{
    public OperationException(String message) {
        super(message);
    }

    public OperationException(Exception cause){
        super(cause);
    }

    public OperationException(String message, Exception cause){
        super(message, cause);
    }
}
