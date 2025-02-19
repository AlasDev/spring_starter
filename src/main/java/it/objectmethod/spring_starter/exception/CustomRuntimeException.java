package it.objectmethod.spring_starter.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomRuntimeException extends RuntimeException {
    private final ErrorBody errorBody;
    private final HttpStatus httpStatus;
    private final Throwable cause;

    /**
     * Constructor of CustomRuntimeException
     * @param message a message explaining what went wrong
     * @param httpStatus status of http request
     * @param cause what throwable was caught in the try-catch
     */
    public CustomRuntimeException(String message, HttpStatus httpStatus, Throwable cause) {
        this.errorBody = new ErrorBody(message);
        this.httpStatus = httpStatus;
        this.cause = cause;
    }
}