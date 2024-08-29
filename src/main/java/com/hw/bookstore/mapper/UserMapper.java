package com.hw.bookstore.mapper;

import com.hw.bookstore.domain.entity.Role;
import com.hw.bookstore.domain.entity.User;
import com.hw.bookstore.dto.request.UserRegistrationRequestDto;
import com.hw.bookstore.dto.response.UserRegistrationResponseDto;
import java.util.Set;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserRegistrationResponseDto toUserRegistrationResponseDto(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    @Mapping(target = "password", expression = "java(encoder.encode(requestDto.password()))")
    User toUser(UserRegistrationRequestDto requestDto,
                Set<Role> roles,
                @Context PasswordEncoder encoder
    );
}
