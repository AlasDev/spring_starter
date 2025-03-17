package it.objectmethod.spring_starter.exception.exceptions;

import it.objectmethod.spring_starter.exception.CustomRuntimeException;
import it.objectmethod.spring_starter.util.Role;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
public class RoleNotAuthorizedException extends CustomRuntimeException {
    final String message;
    final HttpStatus status;
    Role role;
    List<Role> roles;
    String method;

    public RoleNotAuthorizedException(Role roleEnum, String method) {
        this.status = HttpStatus.FORBIDDEN;
        this.role = roleEnum;
        this.method = method;
        this.message = "Role is not authorized to perform this '" + method + "' request.";
    }

    public RoleNotAuthorizedException(List<Role> roleEnums, String method) {
        this.status = HttpStatus.FORBIDDEN;
        this.roles = roleEnums;
        this.method = method;
        this.message = "User does not have a role authorized to perform this '" + method + "' request.";
    }
}