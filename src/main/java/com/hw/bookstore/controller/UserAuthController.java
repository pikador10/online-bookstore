package com.hw.bookstore.controller;

import com.hw.bookstore.dto.request.UserLoginRequestDto;
import com.hw.bookstore.dto.request.UserRegistrationRequestDto;
import com.hw.bookstore.dto.response.UserLoginResponseDto;
import com.hw.bookstore.dto.response.UserRegistrationResponseDto;
import com.hw.bookstore.security.auth.jwt.AuthenticationService;
import com.hw.bookstore.service.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "API:AUTHENTICATION")
@RequiredArgsConstructor
@RestController
@RequestMapping("api/auth")
public class UserAuthController {

    private final UserServiceImpl userServiceImpl;
    private final AuthenticationService authenticationService;

    @Operation(summary = "Register user")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/registration")
    public UserRegistrationResponseDto registerUser(
            @RequestBody @Valid UserRegistrationRequestDto requestDto
    ) {
        return userServiceImpl.register(requestDto);
    }

    @Operation(summary = "Login user")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/login")
    public UserLoginResponseDto loginUser(
            @RequestBody @Valid UserLoginRequestDto requestDto
    ) {
        return authenticationService.authenticate(requestDto);
    }
}
