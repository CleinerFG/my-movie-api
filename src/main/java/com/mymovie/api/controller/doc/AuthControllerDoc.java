package com.mymovie.api.controller.doc;

import com.mymovie.api.dto.request.LoginRequest;
import com.mymovie.api.dto.request.UserRequest;
import com.mymovie.api.dto.response.LoginResponse;
import com.mymovie.api.dto.response.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Authentication", description = "Endpoints for user authentication")
public interface AuthControllerDoc {

    @Operation(
            summary = "Register a new user",
            description = "Creates a new user account in the system."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User registered successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid user data provided", content = @Content()),
            @ApiResponse(responseCode = "409", description = "User already exists", content = @Content())
    })
    ResponseEntity<UserResponse> register(UserRequest dto);

    @Operation(
            summary = "User login",
            description = "Authenticates a user and returns a Bearer Token for authorized requests.",
            security = {} // Remove security requirement for login endpoint
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login successful"),
            @ApiResponse(responseCode = "401", description = "Invalid credentials", content = @Content())
    })
    ResponseEntity<LoginResponse> login(LoginRequest dto);
}