package com.jcontrerast.utils.exception;

import java.io.Serial;

/**
 * Custom exception to indicate assertion failures.
 */
public class AssertionException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new AssertionException with the specified detail message.
     *
     * @param message the detail message, which is saved for later retrieval
     *                by the {@link Throwable#getMessage()} method
     */
    public AssertionException(String message) {
        super(message);
    }
}
