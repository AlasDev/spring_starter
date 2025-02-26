package it.objectmethod.spring_starter.exception.exceptions;

import it.objectmethod.spring_starter.exception.CustomRuntimeException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UnauthorizedException extends CustomRuntimeException {

    HttpStatus status;
    String message;

    public UnauthorizedException(String message, HttpStatus status) {
        this.status = status;
        this.message = message;
    }
}
