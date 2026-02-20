package com.mymovie.api.controller.doc;

import com.mymovie.api.dto.request.StreamingRequest;
import com.mymovie.api.dto.response.StreamingResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Streamings", description = "Endpoints for managing streaming platforms")
public interface StreamingControllerDoc {

    @Operation(
            summary = "Register a new streaming platform",
            description = "Creates a new streaming service record in the system."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Streaming platform created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid streaming data provided", content = @Content()),
            @ApiResponse(responseCode = "401", description = "Unauthorized access", content = @Content())
    })
    ResponseEntity<StreamingResponse> create(StreamingRequest dto);

    @Operation(
            summary = "List all streaming platforms",
            description = "Retrieves a complete list of all registered streaming services."
    )
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list")
    ResponseEntity<List<StreamingResponse>> getAll();

    @Operation(
            summary = "Get streaming platform by ID",
            description = "Retrieves detailed information about a specific streaming service by its unique identifier."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Streaming platform found"),
            @ApiResponse(responseCode = "404", description = "Streaming platform not found", content = @Content())
    })
    ResponseEntity<StreamingResponse> getById(
            @Parameter(description = "Streaming unique identifier") Long id
    );

    @Operation(
            summary = "Update streaming platform",
            description = "Updates the information of an existing streaming service."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Streaming platform updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid data provided", content = @Content()),
            @ApiResponse(responseCode = "404", description = "Streaming platform not found for update", content = @Content())
    })
    ResponseEntity<StreamingResponse> updateById(Long id, StreamingRequest dto);

    @Operation(
            summary = "Remove a streaming platform",
            description = "Deletes a streaming service record permanently from the database."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Streaming platform deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Streaming platform not found", content = @Content())
    })
    ResponseEntity<Void> deleteById(Long id);
}