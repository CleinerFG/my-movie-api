package com.mymovie.api.dto.request;

import com.mymovie.api.infra.constant.ValidationMessages;
import jakarta.validation.constraints.NotEmpty;

public record LoginRequest(
        @NotEmpty(message = ValidationMessages.REQUIRED) String email,
        @NotEmpty(message = ValidationMessages.REQUIRED) String password
) {
}
