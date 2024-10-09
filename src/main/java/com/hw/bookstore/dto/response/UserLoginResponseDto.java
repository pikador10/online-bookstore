package com.hw.bookstore.dto.response;

import lombok.Builder;

@Builder
public record UserLoginResponseDto(String token) {
}
