package com.mymovie.api.dto.request;

public record UserRequest(
        String name,
        String email,
        String password
) {
}
