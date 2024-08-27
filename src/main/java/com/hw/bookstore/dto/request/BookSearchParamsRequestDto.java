package com.hw.bookstore.dto.request;

import java.math.BigDecimal;
import java.util.Set;

public record BookSearchParamsRequestDto(
        Set<String> titles,
        Set<String> authors,
        String isbn,
        BigDecimal lowerPrice,
        BigDecimal upperPrice
) {
}
