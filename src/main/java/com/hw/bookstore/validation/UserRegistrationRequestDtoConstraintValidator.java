package com.hw.bookstore.validation;

import com.hw.bookstore.dto.request.UserRegistrationRequestDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Objects;

public class UserRegistrationRequestDtoConstraintValidator
        implements ConstraintValidator<ValidUserRegistrationRequestDto,
        UserRegistrationRequestDto> {

    @Override
    public boolean isValid(UserRegistrationRequestDto requestDto,
                           ConstraintValidatorContext context
    ) {

        if (!Objects.equals(requestDto.password(), requestDto.repeatPassword())) {
            addErrorMessageForField(
                    "password, repeatPassword",
                    "Password and repeat password must be equals",
                    context
            );

            return false;
        }

        return true;
    }

    private void addErrorMessageForField(String fieldName,
                                         String messageTemplate,
                                         ConstraintValidatorContext context
    ) {
        context.buildConstraintViolationWithTemplate(messageTemplate)
                .addPropertyNode(fieldName)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
    }
}
