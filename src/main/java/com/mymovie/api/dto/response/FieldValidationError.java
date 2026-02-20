package com.mymovie.api.dto.response;

import java.util.Set;

public record FieldValidationError(
        String field,
        Set<String> errors
) {
}
