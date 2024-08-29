package com.hw.bookstore.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UserRegistrationRequestDtoConstraintValidator.class)
public @interface ValidUserRegistrationRequestDto {

    String message() default "Incorrect data";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
