package com.jcontrerast.utils;

import com.jcontrerast.utils.exception.AssertionException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class AssertionsTest {
    @Test
    void testIsNotNull() {
        String test = "IS_NOT_NULL";
        Assertions.isNotNull(test);
    }

    @Test
    void testIsNotNull_fails() {
        String test = null;
        assertThrows(AssertionException.class, () -> Assertions.isNotNull(test));
    }

    @Test
    void testIsPositive() {
        int test = 10;
        Assertions.isPositive(test);

        test = 0;
        Assertions.isPositive(test);
    }

    @Test
    void testIsPositive_fails() {
        int test = -1;
        assertThrows(AssertionException.class, () -> Assertions.isPositive(test));
    }

    @Test
    void testIsGreaterThan() {
        int test = 10;
        Assertions.isGreaterThan(test, 9);
    }

    @Test
    void testIsGreaterThan_fails() {
        int test = 10;
        assertThrows(AssertionException.class, () -> Assertions.isGreaterThan(test, 11));
        assertThrows(AssertionException.class, () -> Assertions.isGreaterThan(test, 10));
    }

    @Test
    void testIsLessThan() {
        int test = 10;
        Assertions.isLessThan(test, 11);
    }

    @Test
    void testIsLessThan_fails() {
        int test = 10;
        assertThrows(AssertionException.class, () -> Assertions.isLessThan(test, 9));
        assertThrows(AssertionException.class, () -> Assertions.isLessThan(test, 10));
    }

    @Test
    void testIsGreaterOrEqualThan() {
        int test = 10;
        Assertions.isGreaterOrEqualThan(test, 9);
        Assertions.isLessOrEqualThan(test, 10);
    }

    @Test
    void testIsGreaterOrEqualThan_fails() {
        int test = 10;
        assertThrows(AssertionException.class, () -> Assertions.isGreaterOrEqualThan(test, 11));
    }

    @Test
    void testIsLessOrEqualThan() {
        int test = 10;
        Assertions.isLessOrEqualThan(test, 11);
        Assertions.isLessOrEqualThan(test, 10);
    }

    @Test
    void testIsLessOrEqualThan_fails() {
        int test = 10;
        assertThrows(AssertionException.class, () -> Assertions.isLessOrEqualThan(test, 9));
    }
}
