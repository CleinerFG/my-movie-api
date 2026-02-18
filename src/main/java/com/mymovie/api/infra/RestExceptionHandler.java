package com.mymovie.api.infra;

import com.mymovie.api.infra.constant.ExceptionMessages;
import com.mymovie.api.infra.exception.ResourceNotFoundException;
import com.mymovie.api.infra.exception.UsernameOrPasswordInvalidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
}
