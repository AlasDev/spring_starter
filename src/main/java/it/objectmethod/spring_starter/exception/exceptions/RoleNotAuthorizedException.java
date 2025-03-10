package it.objectmethod.spring_starter.exception.exceptions;

import it.objectmethod.spring_starter.exception.CustomRuntimeException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class RoleNotAuthorizedException extends CustomRuntimeException {
    final String message;
    final HttpStatus status;
    final String role;
    String method;

    public RoleNotAuthorizedException(String roleEnum, String method) {
        this.status = HttpStatus.FORBIDDEN;
        this.role = roleEnum;
        this.method = method;
        this.message = "Role is not authorized to perform '" + method + "' request.";
    }
}