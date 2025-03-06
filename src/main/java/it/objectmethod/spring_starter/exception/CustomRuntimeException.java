package it.objectmethod.spring_starter.exception;

import lombok.Getter;

@Getter
public class CustomRuntimeException extends RuntimeException {

    /**
     * Constructor of CustomRuntimeException
     */
    public CustomRuntimeException() {
    }
}