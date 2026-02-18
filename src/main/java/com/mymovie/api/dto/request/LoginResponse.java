package com.mymovie.api.dto.request;

import lombok.Builder;

@Builder
public record LoginResponse(
        String token
) {
}
