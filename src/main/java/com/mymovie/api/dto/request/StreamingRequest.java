package com.mymovie.api.dto.request;

import com.mymovie.api.infra.constant.ValidationCode;
import jakarta.validation.constraints.NotEmpty;

public record StreamingRequest(
        @NotEmpty(message = ValidationCode.REQUIRED) String name
) {
}
