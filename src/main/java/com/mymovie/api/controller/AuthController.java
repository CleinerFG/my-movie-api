package com.mymovie.api.controller;

import com.mymovie.api.dto.request.UserRequest;
import com.mymovie.api.dto.response.UserResponse;
import com.mymovie.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/auth")
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody UserRequest dto) {
        UserResponse response = userService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}