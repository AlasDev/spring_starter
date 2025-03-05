package it.objectmethod.spring_starter.exception.exceptions;

import it.objectmethod.spring_starter.exception.CustomRuntimeException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
public class RequiredValueException extends CustomRuntimeException {
    final String message;
    final HttpStatus status;
    final List<String> missingValues;

    public RequiredValueException(String... missingValues) {
        this.status = HttpStatus.BAD_REQUEST;
        this.message = "Values are missing";
        this.missingValues = List.of(missingValues);
    }

    public RequiredValueException(List<String> missingValues) {
        this.status = HttpStatus.BAD_REQUEST;
        this.message = "Values are missing";
        this.missingValues = missingValues;
    }
}
