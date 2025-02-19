package it.objectmethod.spring_starter.exception;

import it.objectmethod.spring_starter.exception.exceptions.BadRequestException;
import it.objectmethod.spring_starter.exception.exceptions.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * Exception handler for AutistaRuntimeException
     * @param ex the exception
     * @return ResponseEntity with error body and appropriate HTTP status
     */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorBody> handleCustomException(NotFoundException ex) {
        ErrorBody errorBody = ex.getErrorBody();
        return ResponseEntity.status(ex.getHttpStatus()).body(errorBody);
    }
    /**
     * Exception handler for AutistaRuntimeException
     * @param ex the exception
     * @return ResponseEntity with error body and appropriate HTTP status
     */
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorBody> handleCustomException(BadRequestException ex) {
        ErrorBody errorBody = ex.getErrorBody();
        return ResponseEntity.status(ex.getHttpStatus()).body(errorBody);
    }
}