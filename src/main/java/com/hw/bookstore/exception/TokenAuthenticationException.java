package com.hw.bookstore.exception;

import org.springframework.http.HttpStatus;

public class TokenAuthenticationException extends AbstractRestException {

    public TokenAuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int responseHttpStatus() {
        return HttpStatus.UNAUTHORIZED.value();
    }

    @Override
    public String responseMessage() {
        return getMessage();
    }
}
