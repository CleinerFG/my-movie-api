package com.mymovie.api.infra;

import com.mymovie.api.dto.response.FieldValidationError;
import com.mymovie.api.infra.constant.ExceptionMessages;
import com.mymovie.api.infra.exception.ResourceNotFoundException;
import com.mymovie.api.infra.exception.UsernameOrPasswordInvalidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.*;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(UsernameOrPasswordInvalidException.class)
    public ProblemDetail handleUsernameOrPasswordInvalid(UsernameOrPasswordInvalidException ex) {
        return createProblemDetail(
                HttpStatus.UNAUTHORIZED,
                ExceptionMessages.USERNAME_OR_PASSWORD_INVALID,
                ex.getMessage()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {

        List<FieldValidationError> errors = extractFieldValidationErrors(ex);

        var problemDetail = createProblemDetail(
                HttpStatus.BAD_REQUEST,
                ExceptionMessages.VALIDATION_FAILED,
                ExceptionMessages.VALIDATION_FAILED
        );

        problemDetail.setProperty("errors", errors);
        return problemDetail;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ProblemDetail handleResourceNotFound(ResourceNotFoundException ex) {
        return createProblemDetail(
                HttpStatus.NOT_FOUND,
                ExceptionMessages.RESOURCE_NOT_FOUND,
                ex.getMessage()
        );
    }

    private ProblemDetail createProblemDetail(HttpStatus status, String title, String detail) {
        var problemDetail = ProblemDetail.forStatusAndDetail(status, detail);
        problemDetail.setTitle(title);
        return problemDetail;
    }

    private List<FieldValidationError> extractFieldValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, Set<String>> errorsMap = new HashMap<>();

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errorsMap.computeIfAbsent(error.getField(), k -> new HashSet<>())
                    .add(error.getDefaultMessage());
        }

        return errorsMap.entrySet()
                .stream()
                .map(entry -> new FieldValidationError(
                        entry.getKey(),
                        entry.getValue())
                )
                .toList();
    }
}
