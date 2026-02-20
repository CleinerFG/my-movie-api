package com.mymovie.api.dto.request;

import com.mymovie.api.infra.constant.ValidationMessages;
import jakarta.validation.constraints.NotEmpty;

public record CategoryRequest(
        @NotEmpty(message = ValidationMessages.REQUIRED) String name
) {
}
