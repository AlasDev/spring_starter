package it.objectmethod.spring_starter.exception.exceptions;

import it.objectmethod.spring_starter.exception.CustomRuntimeException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
public class RequiredValueIsMissingException extends CustomRuntimeException {
    String message;
    HttpStatus status;
    List<String> missingValues;

    public RequiredValueIsMissingException(String... missingValues) {
        this.status = HttpStatus.BAD_REQUEST;
        this.message = "Values are missing";
        this.missingValues = List.of(missingValues);
    }

    public RequiredValueIsMissingException(List<String> missingValues) {
        this.status = HttpStatus.BAD_REQUEST;
        this.message = "Values are missing";
        this.missingValues = missingValues;
    }
}
