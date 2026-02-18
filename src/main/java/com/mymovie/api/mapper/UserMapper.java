package com.mymovie.api.mapper;

import com.mymovie.api.dto.request.UserRequest;
import com.mymovie.api.dto.response.UserResponse;
import com.mymovie.api.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User toEntity(UserRequest dto) {
        return User.builder()
                .name(dto.name())
                .email(dto.email())
                .password(dto.password())
                .build();
    }

    public UserResponse toResponseDTO(User entity) {
        return UserResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .build();
    }
}
