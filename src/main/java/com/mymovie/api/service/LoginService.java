package com.mymovie.api.service;

import com.mymovie.api.dto.request.LoginRequest;
import com.mymovie.api.dto.response.LoginResponse;
import com.mymovie.api.entity.User;
import com.mymovie.api.infra.config.TokenService;
import com.mymovie.api.infra.exception.UsernameOrPasswordInvalidException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public LoginResponse authenticate(LoginRequest dto) {
        try {
            var authToken = new UsernamePasswordAuthenticationToken(
                    dto.email(), dto.password());
            Authentication authentication = authenticationManager.authenticate(authToken);

            User user = (User) authentication.getPrincipal();
            String token = tokenService.generateToken(user);

            return LoginResponse.builder().token(token).build();
        } catch (BadCredentialsException e) {
            throw new UsernameOrPasswordInvalidException();
        }

    }
}
