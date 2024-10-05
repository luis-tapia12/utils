package com.jcontrerast.utils;

import com.jcontrerast.utils.exception.ResourceAlreadyExistsException;
import com.jcontrerast.utils.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExceptionsTest {
    @Test
    void testResourceNotFoundException() {
        String message = "resource not found";
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            throw new ResourceNotFoundException(message);
        });

        assertEquals(message, exception.getMessage());
        assertInstanceOf(RuntimeException.class, exception);
    }

    @Test
    void testResourceAlreadyExistsException() {
        String message = "resource already exists";
        Exception exception = assertThrows(ResourceAlreadyExistsException.class, () -> {
            throw new ResourceAlreadyExistsException(message);
        });

        assertEquals(message, exception.getMessage());
        assertInstanceOf(RuntimeException.class, exception);
    }
}
