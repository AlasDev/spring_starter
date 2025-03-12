package it.objectmethod.spring_starter.exception.exceptions;

import it.objectmethod.spring_starter.exception.CustomRuntimeException;
import it.objectmethod.spring_starter.util.Role;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class RoleNotAuthorizedException extends CustomRuntimeException {
    final String message;
    final HttpStatus status;
    final Role role;
    String method;

    public RoleNotAuthorizedException(Role roleEnum, String method) {
        this.status = HttpStatus.FORBIDDEN;
        this.role = roleEnum;
        this.method = method;
        this.message = "Role is not authorized to perform '" + method + "' request.";
    }
}