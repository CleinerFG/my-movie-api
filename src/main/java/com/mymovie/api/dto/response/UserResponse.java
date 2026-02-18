package com.mymovie.api.dto.response;

import lombok.Builder;

@Builder
public record UserResponse(
        Long id,
        String name,
        String email
) {
}
