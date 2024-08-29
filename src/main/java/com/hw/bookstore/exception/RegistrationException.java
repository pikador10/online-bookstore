package com.hw.bookstore.exception;

import org.springframework.http.HttpStatus;

public class RegistrationException extends AbstractRestException {

    public RegistrationException(String message) {
        super(message);
    }

    @Override
    public int responseHttpStatus() {
        return HttpStatus.CONFLICT.value();
    }

    @Override
    public String responseMessage() {
        return getMessage();
    }
}
