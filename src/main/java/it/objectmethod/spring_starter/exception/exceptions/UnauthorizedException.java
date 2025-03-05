package it.objectmethod.spring_starter.exception.exceptions;

import it.objectmethod.spring_starter.exception.CustomRuntimeException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UnauthorizedException extends CustomRuntimeException {

    final HttpStatus status;
    final String message;

    public UnauthorizedException(String message, HttpStatus status) {
        this.status = status;
        this.message = message;
    }
}
