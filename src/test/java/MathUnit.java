package org.homework;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MathUnit {
    MathOperation math = new MathOperation();

    @Test
    void testFactorial() {
        assertEquals(120, math.factorial(5));
        assertEquals(1, math.factorial(0));
    }

    @Test
    void testTriangleArea() {
        assertEquals(10.0, math.triangleArea(5, 4));
    }

    @Test
    @DisplayName("Проверка арифметических действий")
    void testArithmetic() {
        assertAll(
                () -> assertEquals(10, math.add(7, 3)),
                () -> assertEquals(4, math.subtract(7, 3)),
                () -> assertEquals(21, math.multiply(7, 3)),
                () -> assertEquals(2.0, math.divide(6, 3))
        );
    }

    @Test
    void testCompare() {
        assertEquals("greater", math.compare(10, 5));
        assertEquals("equal", math.compare(5, 5));
    }
}