package it.objectmethod.spring_starter.util;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class CustomRuntimeException extends RuntimeException {
    @Getter
    ErrorBody errorBody;
    @Getter
    HttpStatus httpStatus;

    /**
     * Constructor of CustomRuntimeException
     * @param message message explaining what went wrong
     * @param httpStatus status of http request
     */
    public CustomRuntimeException(String message, HttpStatus httpStatus) {
        this.errorBody = new ErrorBody(message);
        this.httpStatus = httpStatus;
    }
}