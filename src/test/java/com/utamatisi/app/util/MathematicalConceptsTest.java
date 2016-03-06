package com.utamatisi.app.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by titus.chirchir12
 * Date Created 3/5/2016.
 * Package: ${PACKAGE}
 */
public class MathematicalConceptsTest {

    private static final MathematicalConcepts MATHEMATICAL_CONCEPTS = new MathematicalConceptsImpl();

    @Test
    public void fibonacci() {
        Assert.assertEquals(1L, MATHEMATICAL_CONCEPTS.fibonacci(1));
        Assert.assertEquals(1L, MATHEMATICAL_CONCEPTS.fibonacci(2));
        Assert.assertEquals(2L, MATHEMATICAL_CONCEPTS.fibonacci(3));
        Assert.assertEquals(3L, MATHEMATICAL_CONCEPTS.fibonacci(4));
        Assert.assertEquals(5L, MATHEMATICAL_CONCEPTS.fibonacci(5));
        Assert.assertEquals(8L, MATHEMATICAL_CONCEPTS.fibonacci(6));
        Assert.assertEquals(13L, MATHEMATICAL_CONCEPTS.fibonacci(7));
        Assert.assertEquals(267914296L, MATHEMATICAL_CONCEPTS.fibonacci(42));
    }

    @Test
    public void power() {
        Assert.assertEquals(4.0, MATHEMATICAL_CONCEPTS.power(2, 2), 0.0);
        Assert.assertEquals(27.0, MATHEMATICAL_CONCEPTS.power(3, 3), 0.0);
        Assert.assertEquals(1024, MATHEMATICAL_CONCEPTS.power(2, 10), 0.0);
        Assert.assertEquals(21.109, MATHEMATICAL_CONCEPTS.power(1.546, 7), 0.001);
    }

}