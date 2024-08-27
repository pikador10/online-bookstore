package com.hw.bookstore.mapper;

import com.hw.bookstore.domain.entity.User;
import com.hw.bookstore.dto.request.UserRegistrationRequestDto;
import com.hw.bookstore.dto.response.UserRegistrationResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserRegistrationResponseDto toUserRegistrationResponseDto(User user);

    @Mapping(target = "id", ignore = true)
    User toUser(UserRegistrationRequestDto requestDto);
}
