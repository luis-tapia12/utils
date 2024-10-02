package com.jcontrerast.utils.exception;

import java.io.Serial;

public class AssertionException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public AssertionException(String message) {
        super(message);
    }
}
