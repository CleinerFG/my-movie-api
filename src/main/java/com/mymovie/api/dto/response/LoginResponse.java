package com.mymovie.api.dto.response;

import lombok.Builder;

@Builder
public record LoginResponse(
        String token
) {
}
