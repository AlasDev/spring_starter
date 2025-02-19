package it.objectmethod.spring_starter.exception;

import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class ErrorBody {
    private final String message;
    private final Timestamp timestamp;

    /**
     * Constructor of ErrorBody
     * @param message a message that explains what went wrong
     */
    public ErrorBody(String message) {
        this.message = message;
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }
}