package com.mymovie.api.mapper;

import com.mymovie.api.dto.request.MovieRequest;
import com.mymovie.api.dto.response.MovieResponse;
import com.mymovie.api.entity.Category;
import com.mymovie.api.entity.Movie;
import com.mymovie.api.entity.Streaming;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MovieMapper {

    private final CategoryMapper categoryMapper;
    private final StreamingMapper streamingMapper;

    public Movie toEntity(MovieRequest dto) {
        var movie = new Movie();
        updateEntityFromDTO(dto, movie);
        return movie;
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

    public void updateEntityFromDTO(MovieRequest dto, Movie entity) {
        var categories = dto.categoryIds()
                .stream()
                .map(id -> Category.builder().id(id).build())
                .toList();

        var streamings = dto.streamingIds()
                .stream()
                .map(id -> Streaming.builder().id(id).build())
                .toList();

        entity.setTitle(dto.title());
        entity.setDescription(dto.description());
        entity.setReleaseDate(dto.releaseDate());
        entity.setRating(dto.rating());
        entity.setCategories(categories);
        entity.setStreamings(streamings);
    }

    public void patchEntityFromDTO(MovieRequest dto, Movie entity) {
        if (dto.title() != null) entity.setTitle(dto.title());
        if (dto.description() != null) entity.setDescription(dto.description());
        if (dto.releaseDate() != null) entity.setReleaseDate(dto.releaseDate());
        if (dto.rating() != null) entity.setRating(dto.rating());
    }
}
