package com.jcontrerast.utils.exception;

import java.io.Serial;

/**
 * Exception thrown when an attempt is made to create a resource that already exists.
 */
public class ResourceAlreadyExistsException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new ResourceAlreadyExistsException with the specified detail message.
     *
     * @param message the detail message, which is saved for later retrieval
     *                by the {@link Throwable#getMessage()} method
     */
    public ResourceAlreadyExistsException(String message) {
        super(message);
    }
}
