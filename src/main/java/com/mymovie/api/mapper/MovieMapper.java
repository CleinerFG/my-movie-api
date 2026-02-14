package com.mymovie.api.mapper;

import com.mymovie.api.dto.request.MovieRequest;
import com.mymovie.api.dto.response.MovieResponse;
import com.mymovie.api.entity.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MovieMapper {

    private final CategoryMapper categoryMapper;
    private final StreamingMapper streamingMapper;

    public Movie toEntity(MovieRequest dto) {
        return Movie.builder()
                .title(dto.title())
                .description(dto.description())
                .releaseDate(dto.releaseDate())
                .rating(dto.rating())
                .build();

    }

    public MovieResponse toResponseDTO(Movie entity) {

        var categories = entity.getCategories()
                .stream()
                .map(categoryMapper::toResponseDTO)
                .toList();

        var streamings = entity.getStreamings()
                .stream()
                .map(streamingMapper::toResponseDTO)
                .toList();

        return MovieResponse.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .releaseDate(entity.getReleaseDate())
                .rating(entity.getRating())
                .categories(categories)
                .streamings(streamings)
                .build();
    }
}
