package com.mymovie.api.infra.exception;

import com.mymovie.api.infra.constant.AppErrorCode;

public class UsernameOrPasswordInvalidException extends RuntimeException {

    public UsernameOrPasswordInvalidException() {
        super(AppErrorCode.INVALID_CREDENTIALS.name());
    }

    public UsernameOrPasswordInvalidException(String message) {
        super(message);
    }
}
