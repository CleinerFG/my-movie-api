package com.mymovie.api.controller.doc;

import com.mymovie.api.dto.request.MovieRequest;
import com.mymovie.api.dto.response.MovieResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Movies", description = "Endpoints for managing the movie catalog")
public interface MovieControllerDoc {

    @Operation(
            summary = "Register a new movie",
            description = "Creates a new movie record in the system and returns the created resource location."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Movie created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content()),
            @ApiResponse(responseCode = "401", description = "Unauthorized access", content = @Content())
    })
    ResponseEntity<MovieResponse> create(MovieRequest dto);

    @Operation(
            summary = "List all movies",
            description = "Retrieves a complete list of all registered movies."
    )
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list")
    ResponseEntity<List<MovieResponse>> getAll();

    @Operation(
            summary = "Search movies by category",
            description = "Filters and returns a list of movies based on a specific category ID."
    )
    @ApiResponse(responseCode = "200", description = "Successfully retrieved movies for the category")
    ResponseEntity<List<MovieResponse>> getByCategoryId(
            @Parameter(description = "ID of the category to filter by") Long category
    );

    @Operation(
            summary = "Get movie by id",
            description = "Retrieves detailed information about a specific movie by its ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movie found"),
            @ApiResponse(responseCode = "404", description = "Movie not found", content = @Content())
    })
    ResponseEntity<MovieResponse> getById(@Parameter(description = "Movie unique identifier") Long id);

    @Operation(
            summary = "Update an existing movie",
            description = "Updates movie information based on the provided ID and request body."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movie updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid data provided", content = @Content()),
            @ApiResponse(responseCode = "404", description = "Movie not found for update", content = @Content())
    })
    ResponseEntity<MovieResponse> updateById(Long id, MovieRequest dto);

    @Operation(
            summary = "Remove a movie",
            description = "Deletes a movie record permanently from the database."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Movie deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Movie not found", content = @Content())
    })
    ResponseEntity<Void> deleteById(Long id);
}