package com.mymovie.api.infra;

import com.mymovie.api.dto.response.FieldValidationError;
import com.mymovie.api.infra.constant.AppErrorCode;
import com.mymovie.api.infra.exception.ResourceNotFoundException;
import com.mymovie.api.infra.exception.UsernameOrPasswordInvalidException;
import org.springframework.http.ProblemDetail;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.*;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ProblemDetail handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return buildProblemDetail(AppErrorCode.MALFORMED_JSON);
    }

    @ExceptionHandler(UsernameOrPasswordInvalidException.class)
    public ProblemDetail handleUsernameOrPasswordInvalid(UsernameOrPasswordInvalidException ex) {
        return buildProblemDetail(AppErrorCode.INVALID_CREDENTIALS);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {

        List<FieldValidationError> errors = extractFieldValidationErrors(ex);

        var problemDetail = buildProblemDetail(AppErrorCode.VALIDATION_FAILED);

        problemDetail.setProperty("errors", errors);
        return problemDetail;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ProblemDetail handleResourceNotFound(ResourceNotFoundException ex) {
        return buildProblemDetail(AppErrorCode.RESOURCE_NOT_FOUND);
    }

    private ProblemDetail buildProblemDetail(AppErrorCode errorCode) {
        var problemDetail = ProblemDetail.forStatus(errorCode.getHttpStatus());
        problemDetail.setTitle(errorCode.name());
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
