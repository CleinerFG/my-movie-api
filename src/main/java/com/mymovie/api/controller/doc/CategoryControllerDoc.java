package com.mymovie.api.controller.doc;

import com.mymovie.api.dto.request.CategoryRequest;
import com.mymovie.api.dto.response.CategoryResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Categories", description = "Endpoints for managing movie categories")
public interface CategoryControllerDoc {

    @Operation(
            summary = "Create a new category",
            description = "Registers a new category to be used by movies."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Category created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid category data", content = @Content()),
            @ApiResponse(responseCode = "401", description = "Unauthorized access", content = @Content())
    })
    ResponseEntity<CategoryResponse> create(CategoryRequest dto);

    @Operation(
            summary = "List all categories",
            description = "Retrieves a list of all registered movie categories."
    )
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list")
    ResponseEntity<List<CategoryResponse>> getAll();

    @Operation(
            summary = "Get category by ID",
            description = "Retrieves details of a specific category using its unique identifier."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category found"),
            @ApiResponse(responseCode = "404", description = "Category not found", content = @Content())
    })
    ResponseEntity<CategoryResponse> getById(
            @Parameter(description = "Category unique identifier") Long id
    );

    @Operation(
            summary = "Update category",
            description = "Updates the information of an existing category."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid data provided", content = @Content()),
            @ApiResponse(responseCode = "404", description = "Category not found", content = @Content())
    })
    ResponseEntity<CategoryResponse> updateById(Long id, CategoryRequest dto);

    @Operation(
            summary = "Delete category",
            description = "Permanently removes a category from the database."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Category deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Category not found", content = @Content())
    })
    ResponseEntity<Void> deleteById(Long id);
}