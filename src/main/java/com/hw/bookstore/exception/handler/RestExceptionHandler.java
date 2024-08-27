package com.hw.bookstore.exception.handler;

import static org.apache.commons.lang3.StringUtils.SPACE;

import com.hw.bookstore.exception.EntityNotFoundException;
import com.hw.bookstore.exception.RegistrationException;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorBody> handleValidationExceptions(
            MethodArgumentNotValidException ex
    ) {
        List<ErrorMessage> messages = ex.getBindingResult().getAllErrors()
                .stream()
                .map(error -> new ErrorMessage(
                        error.getClass().getName(),
                        error.getCode() + SPACE + error.getDefaultMessage(),
                        ex.getStatusCode().value()))
                .toList();

        return new ResponseEntity<>(
                new ErrorBody(messages, ex.getStackTrace()),
                ex.getStatusCode());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorBody> handleBookNotFoundException(EntityNotFoundException ex) {
        var errorMessages = List.of(new ErrorMessage(
                ex.getClass().getSimpleName(),
                ex.responseMessage(),
                ex.responseHttpStatus())
        );

        return new ResponseEntity<>(
                new ErrorBody(errorMessages, ex.getStackTrace()),
                HttpStatusCode.valueOf(ex.responseHttpStatus()));
    }

    @ExceptionHandler(RegistrationException.class)
    public ResponseEntity<ErrorBody> handleRegistrationException(RegistrationException ex) {
        var errorMessages = List.of(new ErrorMessage(
                ex.getClass().getSimpleName(),
                ex.responseMessage(),
                ex.responseHttpStatus())
        );

        return new ResponseEntity<>(
                new ErrorBody(errorMessages, ex.getStackTrace()),
                HttpStatusCode.valueOf(ex.responseHttpStatus()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorBody> handleAllExceptions(Exception ex) {
        var errorMessages = List.of(new ErrorMessage(
                ex.getClass().getSimpleName(),
                ex.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value())
        );

        return new ResponseEntity<>(
                new ErrorBody(errorMessages, ex.getStackTrace()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
