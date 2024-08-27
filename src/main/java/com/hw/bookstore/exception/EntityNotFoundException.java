package com.hw.bookstore.exception;

import org.springframework.http.HttpStatus;

public class EntityNotFoundException extends AbstractRestException {

    public EntityNotFoundException(String message) {
        super(message);
    }

    @Override
    public int responseHttpStatus() {
        return HttpStatus.NOT_FOUND.value();
    }

    @Override
    public String responseMessage() {
        return getMessage();
    }
}
