package com.hw.bookstore.dto.response;

public record UserRegistrationResponseDto(
        Long id,
        String firstName,
        String lastName,
        String shippingAddress
) {
}
