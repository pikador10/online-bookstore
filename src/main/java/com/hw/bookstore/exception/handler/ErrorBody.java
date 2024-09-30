package com.hw.bookstore.exception.handler;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorBody {

    private List<? extends ErrorMessage> errors = new ArrayList<>();
    private StackTraceElement[] stackTraceElements = new StackTraceElement[]{};
}
