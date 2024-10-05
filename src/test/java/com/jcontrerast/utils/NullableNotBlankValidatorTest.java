package com.jcontrerast.utils;

import com.jcontrerast.utils.validation.NullableNotBlankValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NullableNotBlankValidatorTest {
    private final NullableNotBlankValidator validator = new NullableNotBlankValidator();

    @Test
    void testValidator() {
        assertTrue(validator.isValid(null, null));
        assertTrue(validator.isValid("VALID", null));
        assertFalse(validator.isValid("", null));
        assertFalse(validator.isValid("   ", null));
    }
}
