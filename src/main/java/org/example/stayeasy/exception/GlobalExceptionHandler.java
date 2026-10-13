package org.example.stayeasy.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.time.OffsetDateTime;
import java.util.Map;

//Returnerar felmeddelande till användaren

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        var pd = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);

        pd.setTitle("Validation Failed");
        pd.setDetail("One or more fields are invalid");

        var errors = e.getBindingResult().getFieldErrors().stream()
                .map(err -> Map.of(
                        "field", err.getField(),
                        "rejectedValue", err.getRejectedValue(),
                        "message", err.getDefaultMessage()
                ))
                .toList();

        pd.setProperty("errors", errors);
        pd.setProperty("timestamp", OffsetDateTime.now());

        return pd;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ProblemDetail handleConstraintViolationException(ConstraintViolationException e) {
        var pd = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);

        pd.setTitle("Constraint Violation");
        pd.setDetail("Request parameters are invalid");
        var errors = e.getConstraintViolations().stream()
                .map(err -> Map.of(
                        "propety", err.getPropertyPath().toString(),
                        "invalidValue", err.getInvalidValue(),
                        "message", err.getMessage()
                ))
                .toList();

        pd.setProperty("errors", errors);
        pd.setProperty("timestamp", OffsetDateTime.now());

        return pd;
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ProblemDetail handleHandlerMethodValidationException(HandlerMethodValidationException e) {
        var pd = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);

        pd.setTitle("Validation Failed");
        pd.setDetail("Request parameters/path variables are invalid");

        var errors = e.getParameterValidationResults().stream()
                .flatMap(r -> r.getResolvableErrors().stream()
                        .map(err -> Map.of(
                                "parameter", r.getMethodParameter().getParameterName(),
                                "invalidValue", String.valueOf(r.getArgument()),
                                "message", err.getDefaultMessage()
                        )))
                .toList();

        pd.setProperty("errors", errors);
        pd.setProperty("timestamp", OffsetDateTime.now());
        return pd;
    }
}

