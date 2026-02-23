package com.mymovie.api.dto.request;

import com.mymovie.api.infra.constant.ValidationCode;
import jakarta.validation.constraints.NotEmpty;

public record UserRequest(
        @NotEmpty(message = ValidationCode.REQUIRED) String name,
        @NotEmpty(message = ValidationCode.REQUIRED) String email,
        @NotEmpty(message = ValidationCode.REQUIRED) String password
) {
}
