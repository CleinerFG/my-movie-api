package com.mymovie.api.service;

import com.mymovie.api.dto.request.UserRequest;
import com.mymovie.api.dto.response.UserResponse;
import com.mymovie.api.mapper.UserMapper;
import com.mymovie.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public UserResponse create(UserRequest dto) {
        var user = userMapper.toEntity(dto);

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        var savedUser = userRepository.save(user);

        return userMapper.toResponseDTO(savedUser);
    }
}
