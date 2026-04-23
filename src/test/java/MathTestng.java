import org.homework.MathOperation;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MathTestng {
    MathOperation math = new MathOperation();

    @Test
    public void testFactorial() {
        Assert.assertEquals(math.factorial(4), 24);
        Assert.assertEquals(math.factorial(0), 1);
    }

    @Test
    public void testTriangleArea() {
        Assert.assertEquals(math.triangleArea(8.0, 3.0), 12.0);
    }

    @Test
    public void testArithmetic() {
        Assert.assertEquals(math.add(15, 15), 30);
        Assert.assertEquals(math.subtract(20, 30), -10);
        Assert.assertEquals(math.multiply(5, 5), 25);
        Assert.assertEquals(math.divide(10, 4), 2.5, 0.001);
    }

    @Test
    public void testCompare() {
        Assert.assertEquals(math.compare(100, 1), "greater");
        Assert.assertEquals(math.compare(5, 5), "equal");
    }
}