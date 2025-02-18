package it.objectmethod.spring_starter.util;

import lombok.Getter;

import java.sql.Timestamp;

public class ErrorBody {
    @Getter
    private final String message;
    @Getter
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