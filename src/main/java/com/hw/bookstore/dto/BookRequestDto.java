package com.hw.bookstore.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public record BookRequestDto(

        @NotNull(message = "Title can not be null")
        @Size(min = 1, max = 255, message = "Title size must be between 1 and 255 symbols")
        String title,

        @NotNull(message = "Author can not be null")
        @Size(min = 1, max = 255, message = "Author size must be between 1 and 255 symbols")
        String author,

        @NotNull(message = "Isbn can not be null")
        @Size(min = 1, max = 255, message = "Isbn size must be between 1 and 255 symbols")
        String isbn,

        @NotNull(message = "Price can not be null")
        @DecimalMin(value = "0.00", message = "Minimal price can not be less than 0.00")
        @DecimalMax(value = "1000000.00", message = "Max price can not be more than 1000000.00")
        BigDecimal price,

        String description,

        String coverImage
) {
}
