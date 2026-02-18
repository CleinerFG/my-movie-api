package com.mymovie.api.dto.request;

public record LoginRequest(
        String email,
        String password
) {
}
