package it.objectmethod.spring_starter.exception;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
import java.util.List;

@Getter
@JsonPropertyOrder({"timestamp", "status", "error", "message"})
public class ErrorBody {
    private final Timestamp timestamp;
    private final Long status;
    private final String error;
    private final List<String> message;

    /**
     * Constructor for ErrorBody
     *
     * @param error error name
     * @param message error message
     */
    public ErrorBody(String error, HttpStatus status, List<String> message) {
        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.status = (long) status.value();
        this.message = message;
        this.error = error;
    }
}