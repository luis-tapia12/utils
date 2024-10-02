package com.jcontrerast.utils;

import com.jcontrerast.utils.exception.AssertionException;

public class Assertions {
    private final static String SUFFIX = "Validation failed: ";

    public static void isNotNull(Object object) {
        if (object == null)
            throw new AssertionException(SUFFIX + Messages.ASSERTION_IS_NOT_NULL_ERROR);
    }

    public static void isPositive(Number number) {
        isNotNull(number);

        if (number.intValue() < 0)
            throw new AssertionException(SUFFIX + Messages.ASSERTION_IS_POSITIVE_ERROR);
    }

    public static void isGreaterThan(Number numberA, Number numberB) {
        isNotNull(numberA);
        isNotNull(numberB);

        if (numberA.doubleValue() <= numberB.doubleValue())
            throw new AssertionException(SUFFIX + Messages.ASSERTION_IS_GREATER_THAN_ERROR + numberB);
    }

    public static void isLessThan(Number numberA, Number numberB) {
        isNotNull(numberA);
        isNotNull(numberB);

        if (numberA.doubleValue() >= numberB.doubleValue())
            throw new AssertionException(SUFFIX + Messages.ASSERTION_IS_LESS_THAN_ERROR + numberB);
    }

    public static void isGreaterOrEqualThan(Number numberA, Number numberB) {
        isNotNull(numberA);
        isNotNull(numberB);

        if (numberA.doubleValue() < numberB.doubleValue())
            throw new AssertionException(SUFFIX + Messages.ASSERTION_IS_GREATER_OR_EQUAL_THAN_ERROR + numberB);
    }

    public static void isLessOrEqualThan(Number numberA, Number numberB) {
        isNotNull(numberA);
        isNotNull(numberB);

        if (numberA.doubleValue() > numberB.doubleValue())
            throw new AssertionException(SUFFIX + Messages.ASSERTION_IS_LESS_OR_EQUAL_THAN_ERROR + numberB);
    }
}
