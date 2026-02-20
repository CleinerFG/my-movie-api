package com.mymovie.api.infra.config;

import lombok.Builder;

@Builder
public record JWTUserData(
        Long id,
        String email
) {
}
