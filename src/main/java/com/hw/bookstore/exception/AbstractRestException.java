package com.hw.bookstore.exception;

public abstract class AbstractRestException extends RuntimeException {

    protected AbstractRestException(String message) {
        super(message);
    }

    protected AbstractRestException(String message, Throwable cause) {
        super(message, cause);
    }

    public abstract int responseHttpStatus();

    public abstract String responseMessage();
}
