package com.mymovie.api.infra.exception;

import com.mymovie.api.infra.constant.AppErrorCode;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException() {
        super(AppErrorCode.RESOURCE_NOT_FOUND.name());
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
