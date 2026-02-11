package com.mymovie.api.dto.response;

import lombok.Builder;

@Builder
public record CategoryResponse(Long id, String name) {
}
