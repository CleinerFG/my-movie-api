package com.mymovie.api.dto.request;

import com.mymovie.api.infra.constant.ValidationCode;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank(message = ValidationCode.REQUIRED) String email,
        @NotBlank(message = ValidationCode.REQUIRED) String password
) {
}
