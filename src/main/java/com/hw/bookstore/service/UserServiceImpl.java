package com.hw.bookstore.service;

import com.hw.bookstore.domain.entity.User;
import com.hw.bookstore.domain.repository.UserRepository;
import com.hw.bookstore.dto.request.UserRegistrationRequestDto;
import com.hw.bookstore.dto.response.UserRegistrationResponseDto;
import com.hw.bookstore.exception.RegistrationException;
import com.hw.bookstore.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserRegistrationResponseDto register(UserRegistrationRequestDto requestDto) {
        validateUserExistence(requestDto.email());
        User user = userRepository.save(userMapper.toUser(requestDto));
        return userMapper.toUserRegistrationResponseDto(user);
    }

    private void validateUserExistence(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new RegistrationException(("User with email: '%s' already exists"
                    .formatted(email)));
        }
    }
}
