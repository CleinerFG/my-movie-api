package com.mymovie.api.service;

import com.mymovie.api.dto.request.MovieRequest;
import com.mymovie.api.dto.response.MovieResponse;
import com.mymovie.api.entity.Movie;
import com.mymovie.api.infra.exception.ResourceNotFoundException;
import com.mymovie.api.mapper.MovieMapper;
import com.mymovie.api.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    public MovieResponse create(MovieRequest dto) {
        var movie = movieMapper.toEntity(dto);
        var savedMovie = movieRepository.save(movie);

        return movieMapper.toResponseDTO(savedMovie);
    }

    public List<MovieResponse> findAll() {
        var movies = movieRepository.findAll();

        return movies.stream()
                .map(movieMapper::toResponseDTO)
                .toList();
    }

    public MovieResponse findById(Long id) {
        Optional<Movie> optMovie = movieRepository.findById(id);

        return optMovie
                .map(movieMapper::toResponseDTO)
                .orElseThrow(() -> new ResourceNotFoundException("movieNotFound"));
    }

    public MovieResponse updateById(Long id, MovieRequest dto) {
        Optional<Movie> optMovie = movieRepository.findById(id);

        return optMovie.map(movie->{
            movieMapper.patchEntityFromDTO(dto, movie);
            var savedMovie = movieRepository.save(movie);

            return movieMapper.toResponseDTO(savedMovie);
        }).orElseThrow(()-> new ResourceNotFoundException("movieNotFound"));
    }

    public void deleteById(Long id) {
        if (!movieRepository.existsById(id)){
            throw  new ResourceNotFoundException("movieNotFound");
        }

        movieRepository.deleteById(id);
    }
}
