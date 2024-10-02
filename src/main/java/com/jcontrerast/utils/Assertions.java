package com.jcontrerast.utils;

import com.jcontrerast.utils.exception.AssertionException;

public class Assertions {
    public static void isNotNull(Object object) {
        if (object == null)
            throw new AssertionException();
    }

    public static void isPositive(Number number) {
        isNotNull(number);

        if (number.intValue() < 0)
            throw new AssertionException();
    }

    public static void isGreaterThan(Number numberA, Number numberB) {
        isNotNull(numberA);
        isNotNull(numberB);

        if (numberA.doubleValue() <= numberB.doubleValue())
            throw new AssertionException();
    }

    public static void isLessThan(Number numberA, Number numberB) {
        isNotNull(numberA);
        isNotNull(numberB);

        if (numberA.doubleValue() >= numberB.doubleValue())
            throw new AssertionException();
    }

    public static void isGreaterOrEqualThan(Number numberA, Number numberB) {
        isNotNull(numberA);
        isNotNull(numberB);

        if (numberA.doubleValue() < numberB.doubleValue())
            throw new AssertionException();
    }

    public static void isLessOrEqualThan(Number numberA, Number numberB) {
        isNotNull(numberA);
        isNotNull(numberB);

        if (numberA.doubleValue() > numberB.doubleValue())
            throw new AssertionException();
    }
}
