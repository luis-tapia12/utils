package com.jcontrerast.utils;

import com.jcontrerast.utils.exception.AssertionException;

public class Assertions {
    private final static String SUFFIX = "Validation failed: ";

    /**
     * Asserts that the given object is not null.
     *
     * @param object the object to check
     * @throws AssertionException if the object is null
     */
    public static void isNotNull(Object object) {
        if (object == null)
            throw new AssertionException(SUFFIX + Messages.ASSERTION_IS_NOT_NULL_ERROR);
    }

    /**
     * Asserts that the given number is positive.
     *
     * @param number the number to check
     * @throws AssertionException if the number is null or not positive
     */
    public static void isPositive(Number number) {
        isNotNull(number);

        if (number.intValue() < 0)
            throw new AssertionException(SUFFIX + Messages.ASSERTION_IS_POSITIVE_ERROR);
    }

    /**
     * Asserts that the first number is greater than the second number.
     *
     * @param numberA the first number to check
     * @param numberB the second number to compare against
     * @throws AssertionException if either number is null or if numberA is not greater than numberB
     */
    public static void isGreaterThan(Number numberA, Number numberB) {
        isNotNull(numberA);
        isNotNull(numberB);

        if (numberA.doubleValue() <= numberB.doubleValue())
            throw new AssertionException(SUFFIX + Messages.ASSERTION_IS_GREATER_THAN_ERROR + numberB);
    }

    /**
     * Asserts that the first number is less than the second number.
     *
     * @param numberA the first number to check
     * @param numberB the second number to compare against
     * @throws AssertionException if either number is null or if numberA is not less than numberB
     */
    public static void isLessThan(Number numberA, Number numberB) {
        isNotNull(numberA);
        isNotNull(numberB);

        if (numberA.doubleValue() >= numberB.doubleValue())
            throw new AssertionException(SUFFIX + Messages.ASSERTION_IS_LESS_THAN_ERROR + numberB);
    }

    /**
     * Asserts that the first number is greater than or equal to the second number.
     *
     * @param numberA the first number to check
     * @param numberB the second number to compare against
     * @throws AssertionException if either number is null or if numberA is less than numberB
     */
    public static void isGreaterOrEqualThan(Number numberA, Number numberB) {
        isNotNull(numberA);
        isNotNull(numberB);

        if (numberA.doubleValue() < numberB.doubleValue())
            throw new AssertionException(SUFFIX + Messages.ASSERTION_IS_GREATER_OR_EQUAL_THAN_ERROR + numberB);
    }

    /**
     * Asserts that the first number is less than or equal to the second number.
     *
     * @param numberA the first number to check
     * @param numberB the second number to compare against
     * @throws AssertionException if either number is null or if numberA is greater than numberB
     */
    public static void isLessOrEqualThan(Number numberA, Number numberB) {
        isNotNull(numberA);
        isNotNull(numberB);

        if (numberA.doubleValue() > numberB.doubleValue())
            throw new AssertionException(SUFFIX + Messages.ASSERTION_IS_LESS_OR_EQUAL_THAN_ERROR + numberB);
    }
}
