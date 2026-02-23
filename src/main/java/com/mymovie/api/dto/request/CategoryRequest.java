package com.mymovie.api.dto.request;

import com.mymovie.api.infra.constant.ValidationCode;
import jakarta.validation.constraints.NotBlank;

public record CategoryRequest(
        @NotBlank(message = ValidationCode.REQUIRED) String name
) {
}
