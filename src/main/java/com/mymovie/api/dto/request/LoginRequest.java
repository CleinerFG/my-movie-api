package com.mymovie.api.dto.request;

import com.mymovie.api.infra.constant.ValidationCode;
import jakarta.validation.constraints.NotEmpty;

public record LoginRequest(
        @NotEmpty(message = ValidationCode.REQUIRED) String email,
        @NotEmpty(message = ValidationCode.REQUIRED) String password
) {
}
