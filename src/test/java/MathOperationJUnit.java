import org.homework.MathOperation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MathOperationJUnit {
    MathOperation math = new MathOperation();

    @Test
    void testFactorial() {
        assertEquals(720, math.factorial(6));
        assertEquals(1, math.factorial(0));
    }

    @Test
    void testTriangleArea() {
        assertEquals(25.0, math.triangleArea(10.0, 5.0));
    }

    @Test
    @DisplayName("Проверка арифметических действий")
    void testArithmetic() {
        assertAll(
                () -> assertEquals(20, math.add(12, 8)),
                () -> assertEquals(50, math.subtract(100, 50)),
                () -> assertEquals(32, math.multiply(4, 8)),
                () -> assertEquals(2.5, math.divide(5, 2))
        );
    }

    @Test
    void testCompare() {
        assertEquals("less", math.compare(10, 50));
        assertEquals("equal", math.compare(7, 7));
    }
}