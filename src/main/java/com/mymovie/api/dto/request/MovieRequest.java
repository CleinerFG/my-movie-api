package com.mymovie.api.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.List;

public record MovieRequest(
        String title,
        String description,

        @JsonFormat(pattern = "yyyy-MM-dd") // ISO 8601
        LocalDate releaseDate,

        Double rating,
        List<Long> categoryIds,
        List<Long> streamingIds
) {
}
