package com.mymovie.api.dto.request;

import java.time.LocalDate;
import java.util.List;

public record MovieRequest(
        String title,
        String description,
        LocalDate releaseDate,
        Double rating,
        List<Long> categoryIds,
        List<Long> streamingIds
) {
}
