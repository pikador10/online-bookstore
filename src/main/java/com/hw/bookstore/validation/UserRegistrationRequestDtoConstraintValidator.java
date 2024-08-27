package com.hw.bookstore.validation;

import com.hw.bookstore.dto.request.UserRegistrationRequestDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UserRegistrationRequestDtoConstraintValidator
        implements ConstraintValidator<ValidUserRegistrationRequestDto,
        UserRegistrationRequestDto> {

    @Override
    public boolean isValid(UserRegistrationRequestDto requestDto,
                           ConstraintValidatorContext context
    ) {
        return requestDto.password().equals(requestDto.repeatPassword());
    }
}
