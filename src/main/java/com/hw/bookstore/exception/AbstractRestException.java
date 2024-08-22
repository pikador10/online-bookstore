package com.hw.bookstore.exception;

public abstract class AbstractRestException extends RuntimeException {

    protected AbstractRestException(String message) {
        super(message);
    }

    public abstract int responseHttpStatus();

    public abstract String responseMessage();
}
