package com.hw.bookstore.dto.request;

import com.hw.bookstore.validation.ValidUserRegistrationRequestDto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@ValidUserRegistrationRequestDto
public record UserRegistrationRequestDto(

        @NotNull(message = "First name can not be null")
        @Size(min = 1, max = 64, message = "First name size must be between 1 and 64 symbols")
        String firstName,

        @NotNull(message = "Last name can not be null")
        @Size(min = 1, max = 64, message = "Last name size must be between 1 and 64 symbols")
        String lastName,

        @NotNull(message = "Email can not be null")
        @Pattern(regexp = EMAIL_VALIDATION_PATTERN, message = EMAIL_VALIDATION_ERROR_MESSAGE)
        String email,

        @NotNull(message = "Password can not be null")
        @Size(min = 1, max = 64, message = "Password must be between 1 and 64 symbols")
        String password,

        @NotNull(message = "Repeat password can not be null")
        @Size(min = 1, max = 64, message = "Repeat password must be between 1 and 64 symbols")
        String repeatPassword,

        @Size(min = 8, max = 255, message = "Shipping address must be between 8 and 255 symbols")
        String shippingAddress
) {
    public static final String EMAIL_VALIDATION_PATTERN = "^(?:\"[\"(),:;<>@\\[\\\\\\]\\t a-zA-Z\\"
            + "d.]{1,62}\"|(?!\\.)[\\w!#$%&'*+/=?^`{|}~\\-]{1,64}(?:\\.[\\w!#$%&'*+/=?^`{|}~\\-]"
            + "+)*)@(((?![.-])[a-zA-Z\\d\\-])+(?:[.-](?![0-9]+$)[a-zA-Z\\d]{2,63})*)$";

    public static final String EMAIL_VALIDATION_ERROR_MESSAGE = "Incorrect email format";
}
