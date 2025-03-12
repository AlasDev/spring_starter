package it.objectmethod.spring_starter.exception;

import it.objectmethod.spring_starter.exception.exceptions.EmailAlreadyRegisteredException;
import it.objectmethod.spring_starter.exception.exceptions.RequiredValueException;
import it.objectmethod.spring_starter.exception.exceptions.UnauthorizedException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Exception handler for AutistaRuntimeException
     * Thrown by various accessor methods to indicate that the element being requested does not exist.
     *
     * @param ex the exception
     * @return ResponseEntity with error body and appropriate HTTP status
     */
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorBody> handleCustomException(NoSuchElementException ex) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        List<String> errorMessages = List.of(ex.getMessage());
        ErrorBody errorBody = new ErrorBody("Could not find element", status, errorMessages);
        return new ResponseEntity<>(errorBody, status);
    }

    /**
     * Exception handler for MethodArgumentTypeMismatchException.
     * Exception raised while resolving a controller method argument
     *
     * @param ex the exception
     * @return ResponseEntity with error body and appropriate HTTP status
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorBody> handleCustomException(MethodArgumentTypeMismatchException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorBody errorBody = new ErrorBody("Type received is not valid",
                status,
                List.of(ex.getName() + " of type: '" + ex.getValue().getClass().getSimpleName() + "' should be of type: '" + ex.getRequiredType().getSimpleName() + "' instead.")
        );
        return ResponseEntity.status(status).body(errorBody);
    }

    /**
     * Exception handler for EntityNotFoundException.
     * Thrown by the persistence provider when an entity reference is accessed but the entity does not exist.
     *
     * @param ex the exception
     * @return ResponseEntity with error body and appropriate HTTP status
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorBody> handleCustomException(EntityNotFoundException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorBody errorBody = new ErrorBody("Entity was not found",
                status,
                List.of(ex.getMessage())
        );
        return ResponseEntity.status(status).body(errorBody);
    }

    /**
     * Exception handler for MethodArgumentNotValidException.
     * It is thrown every time an argument annotated with {@code @Validated}(or one that gets validated automatically) tries to get validated but fails.
     *
     * @param ex the exception
     * @return ResponseEntity with error body and appropriate HTTP status
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorBody> handleCustomException(MethodArgumentNotValidException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        List<String> errorMessages = List.of();

        if (ex.hasFieldErrors()) {
            errorMessages = ex.getBindingResult()
                    .getFieldErrors()
                    .stream()
                    .map(fieldError -> String.format("Field '%s': %s (rejected value: '%s')",
                            fieldError.getField(),
                            fieldError.getDefaultMessage(),
                            fieldError.getRejectedValue()))
                    .toList();
        } else if (ex.hasGlobalErrors()) {
            errorMessages = ex.getBindingResult()
                    .getAllErrors()
                    .stream()
                    .map(error -> String.format("Object '%s': %s",
                            error.getObjectName(),
                            error.getDefaultMessage()))
                    .toList();
        }

        ErrorBody errorBody = new ErrorBody("Object Validation Error", status, errorMessages);
        return ResponseEntity.status(status).body(errorBody);
    }

    /**
     * Exception handler for custom exception RequiredValueIsMissingException.
     *
     * @param ex the exception
     * @return ResponseEntity with error body and appropriate HTTP status
     */
    @ExceptionHandler(RequiredValueException.class)
    public ResponseEntity<ErrorBody> handleCustomException(RequiredValueException ex) {
        HttpStatus status = ex.getStatus();
        List<String> errorMessages = ex.getMissingValues()
                .stream()
                .map(error -> "Required value '" + error + "' is missing.")
                .toList();
        ErrorBody errorBody = new ErrorBody(ex.getMessage(), status, errorMessages);
        return ResponseEntity.status(status).body(errorBody);
    }

    /**
     * Exception handler for HttpMessageNotReadableException.
     *
     * @param ex the exception
     * @return ResponseEntity with error body and appropriate HTTP status
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorBody> handleCustomException(HttpMessageNotReadableException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorBody errorBody = new ErrorBody("Cannot read values", status, List.of(ex.getLocalizedMessage()));
        return ResponseEntity.status(status).body(errorBody);
    }

    /**
     * Exception handler for SQLException.
     *
     * @param ex the exception
     * @return ResponseEntity with error body and appropriate HTTP status
     */
    @ExceptionHandler(SQLException.class)
    public ResponseEntity<ErrorBody> handleCustomException(SQLException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorBody errorBody = new ErrorBody("SQL error", status, List.of(ex.getLocalizedMessage()));
        return ResponseEntity.status(status).body(errorBody);
    }

    /**
     * Exception handler for UnauthorizedException.
     *
     * @param ex the exception
     * @return ResponseEntity with error body and appropriate HTTP status
     */
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorBody> handleCustomException(UnauthorizedException ex) {
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        ErrorBody errorBody = new ErrorBody("Unauthorized", status, List.of(ex.getLocalizedMessage()));
        return ResponseEntity.status(status).body(errorBody);
    }

    @ExceptionHandler(EmailAlreadyRegisteredException.class)
    public ResponseEntity<ErrorBody> handleCustomException(EmailAlreadyRegisteredException ex) {
        HttpStatus status = HttpStatus.CONFLICT;
        ErrorBody errorBody = new ErrorBody("Email already registered", status, List.of("Email: '" + ex.getEmail() + "' already registered to another user."));
        return ResponseEntity.status(status).body(errorBody);
    }
}