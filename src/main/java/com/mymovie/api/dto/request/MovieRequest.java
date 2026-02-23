package com.mymovie.api.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mymovie.api.infra.constant.ValidationCode;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.List;

public record MovieRequest(
        @NotBlank(message = ValidationCode.REQUIRED)
        String title,

        String description,

        @JsonFormat(pattern = "yyyy-MM-dd") // ISO 8601
        LocalDate releaseDate,

        Double rating,
        List<Long> categoryIds,
        List<Long> streamingIds
) {
}
