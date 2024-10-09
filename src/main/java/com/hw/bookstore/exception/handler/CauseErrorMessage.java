package com.hw.bookstore.exception.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CauseErrorMessage extends ErrorMessage {

    private String causeException;

    private String causeMessage;

    public CauseErrorMessage(String exception, String message, int httpStatus,
                             String causeException, String causeMessage) {
        super(exception, message, httpStatus);
        this.causeException = causeException;
        this.causeMessage = causeMessage;
    }
}
