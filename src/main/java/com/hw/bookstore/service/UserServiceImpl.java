package com.hw.bookstore.service;

import static com.hw.bookstore.enums.RoleName.USER;

import com.hw.bookstore.domain.entity.Role;
import com.hw.bookstore.domain.entity.User;
import com.hw.bookstore.domain.repository.RoleRepository;
import com.hw.bookstore.domain.repository.UserRepository;
import com.hw.bookstore.dto.request.UserRegistrationRequestDto;
import com.hw.bookstore.dto.response.UserRegistrationResponseDto;
import com.hw.bookstore.enums.RoleName;
import com.hw.bookstore.exception.EntityNotFoundException;
import com.hw.bookstore.exception.RegistrationException;
import com.hw.bookstore.mapper.UserMapper;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public UserRegistrationResponseDto register(UserRegistrationRequestDto requestDto) {
        validateUserExistence(requestDto.email());
        User user = userMapper.toUser(requestDto, Set.of(getRoleByName(USER)), passwordEncoder);
        userRepository.save(user);
        return userMapper.toUserRegistrationResponseDto(user);
    }

    private void validateUserExistence(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new RegistrationException(("User with email: '%s' already exists"
                    .formatted(email)));
        }
    }

    private Role getRoleByName(RoleName role) {
        return roleRepository.findByRole(role)
                .orElseThrow(() -> new EntityNotFoundException("Role was not found with name: '%s'"
                        .formatted(role)));
    }
}
