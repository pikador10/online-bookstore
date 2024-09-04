package com.hw.bookstore.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserLoginRequestDto(

        @NotNull(message = "Email can not be null")
        @Pattern(regexp = EMAIL_VALIDATION_PATTERN, message = EMAIL_VALIDATION_ERROR_MESSAGE)
        String email,

        @NotNull(message = "Password can not be null")
        @Size(min = 1, max = 64, message = "Password must be between 1 and 64 symbols")
        String password
) {
    public static final String EMAIL_VALIDATION_PATTERN = "^(?:\"[\"(),:;<>@\\[\\\\\\]\\t a-zA-Z\\"
            + "d.]{1,62}\"|(?!\\.)[\\w!#$%&'*+/=?^`{|}~\\-]{1,64}(?:\\.[\\w!#$%&'*+/=?^`{|}~\\-]"
            + "+)*)@(((?![.-])[a-zA-Z\\d\\-])+(?:[.-](?![0-9]+$)[a-zA-Z\\d]{2,63})*)$";

    public static final String EMAIL_VALIDATION_ERROR_MESSAGE = "Incorrect email format";
}
