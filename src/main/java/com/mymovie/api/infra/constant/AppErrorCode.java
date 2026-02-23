package com.mymovie.api.infra.constant;

import org.springframework.http.HttpStatus;

public enum AppErrorCode {

    MALFORMED_JSON(HttpStatus.BAD_REQUEST),
    INVALID_CREDENTIALS(HttpStatus.UNAUTHORIZED),
    VALIDATION_FAILED(HttpStatus.UNPROCESSABLE_ENTITY),
    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND);

    private final HttpStatus status;

    AppErrorCode(HttpStatus status) {
        this.status = status;
    }

    public HttpStatus getHttpStatus() {
        return status;
    }
}
