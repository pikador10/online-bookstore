package com.hw.bookstore.security.auth.jwt;

import com.hw.bookstore.domain.entity.User;
import com.hw.bookstore.dto.request.UserLoginRequestDto;
import com.hw.bookstore.dto.response.UserLoginResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;

    public UserLoginResponseDto authenticate(UserLoginRequestDto requestDto) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(requestDto.email(), requestDto.password()));
        String token = tokenProvider.generateToken((User) authenticate.getPrincipal());
        return new UserLoginResponseDto(token);
    }
}
