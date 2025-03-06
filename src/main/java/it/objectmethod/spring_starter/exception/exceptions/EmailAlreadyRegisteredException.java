package it.objectmethod.spring_starter.exception.exceptions;

import it.objectmethod.spring_starter.exception.CustomRuntimeException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class EmailAlreadyRegisteredException extends CustomRuntimeException {
    final String message;
    final HttpStatus status;
    final String email;

    public EmailAlreadyRegisteredException(String email) {
        this.status = HttpStatus.CONFLICT;
        this.message = "Email already registered";
        this.email = email;
    }
}
