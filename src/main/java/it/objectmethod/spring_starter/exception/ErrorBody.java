package it.objectmethod.spring_starter.exception;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
import java.util.List;

@Getter
@JsonPropertyOrder({"timestamp", "status", "message", "errors"})
public class ErrorBody {
    private final Timestamp timestamp;
    private final Long status;
    private final String message;
    private final List<String> errors;

    /**
     * Constructor for ErrorBody
     * @param message error message
     * @param errors list of errors
     */
    public ErrorBody(String message, HttpStatus status, List<String> errors) {
        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.status = (long) status.value();
        this.message = message;
        this.errors = errors;
    }
}