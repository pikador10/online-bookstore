package com.hw.bookstore.service;

import com.hw.bookstore.dto.request.UserRegistrationRequestDto;
import com.hw.bookstore.dto.response.UserRegistrationResponseDto;

public interface UserService {

    UserRegistrationResponseDto register(UserRegistrationRequestDto requestDto);
}
