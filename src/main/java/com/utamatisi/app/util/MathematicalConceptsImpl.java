package com.utamatisi.app.util;

/**
 * Created by titus.chirchir12
 * Date Created 3/5/2016.
 * Package: ${PACKAGE}
 */
public class MathematicalConceptsImpl implements MathematicalConcepts {

    public long fibonacci(int number) {
        return fibonacci(number, 1, 1);
    }

    private long fibonacci(int number, long one, long two) {
        return number <= 2 ? two : fibonacci(--number, two, one + two);
    }

    public double power(double value, int exponent) {
        return power(value, value, exponent);
    }
    private double power(double initialValue, double value, int exponent)
    {
        return exponent == 0 ? 1 : (exponent == 1 ? value : power(initialValue, value * initialValue, --exponent));

    }
}
