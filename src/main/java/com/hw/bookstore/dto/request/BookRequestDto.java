package com.hw.bookstore.dto.request;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
        @Pattern(regexp = ISBN_VALIDATION_PATTERN, message = ISBN_VALIDATION_ERROR_MESSAGE)
        String isbn,

        @NotNull(message = "Price can not be null")
        @DecimalMin(value = "0.00", message = "Minimal price can not be less than 0.00")
        @DecimalMax(value = "1000000.00", message = "Max price can not be more than 1000000.00")
        BigDecimal price,

        String description,

        String coverImage
) {
    private static final String ISBN_VALIDATION_PATTERN = "^(?:ISBN(?:-10)?:? )?(?=[0-9X]{10}$|"
            + "(?=(?:[0-9]+[- ]){3})[- 0-9X]{13}$)[0-9]{1,5}[- ]?[0-9]+[- ]?[0-9]+[- ]?[0-9X]$";

    private static final String ISBN_VALIDATION_ERROR_MESSAGE = "Invalid ISBN-10 format. "
            + "Ensure it follows standard structure (e.g., 0-596-52068-9).";
}
