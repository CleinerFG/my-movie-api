package com.mymovie.api.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record MovieResponse(
        Long id,
        String title,
        String description,

        @JsonFormat(pattern = "yyyy-MM-dd") // ISO 8601
        LocalDate releaseDate,

        Double rating,
        List<CategoryResponse> categories,
        List<StreamingResponse> streamings
) {
}
