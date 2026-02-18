package com.mymovie.api.infra;

import com.mymovie.api.infra.constant.ExceptionMessages;
import com.mymovie.api.infra.exception.ResourceNotFoundException;
import com.mymovie.api.infra.exception.UsernameOrPasswordInvalidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @SuppressWarnings("unused")
    @ExceptionHandler(UsernameOrPasswordInvalidException.class)
    public ResponseEntity<ProblemDetail> handleUsernameOrPasswordInvalidException(
            UsernameOrPasswordInvalidException ex) {
        var problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.UNAUTHORIZED,
                ex.getMessage()
        );

        problemDetail.setTitle(ExceptionMessages.USERNAME_OR_PASSWORD_INVALID);

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(problemDetail);
    }

    @SuppressWarnings("unused")
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleResourceNotFoundException(ResourceNotFoundException ex) {
        var problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND,
                ex.getMessage()
        );

        problemDetail.setTitle(ExceptionMessages.RESOURCE_NOT_FOUND);

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(problemDetail);
    }
}
