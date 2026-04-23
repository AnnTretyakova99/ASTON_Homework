import org.homework.MathOperation;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MathOperationTestng {
    MathOperation math = new MathOperation();

    @Test
    public void testFactorial() {
        Assert.assertEquals(math.factorial(5), 120);
        Assert.assertEquals(math.factorial(0), 1);
    }

    @Test
    public void testTriangleArea() {
        Assert.assertEquals(math.triangleArea(5.0, 4.0), 10.0);
    }

    @Test
    public void testArithmetic() {
        Assert.assertEquals(math.add(7, 3), 10);
        Assert.assertEquals(math.subtract(7, 3), 4);
        Assert.assertEquals(math.multiply(7, 3), 21);
        Assert.assertEquals(math.divide(6, 3), 2.0);
    }

    @Test
    public void testCompare() {
        Assert.assertEquals(math.compare(10, 5),"greater");
        Assert.assertEquals(math.compare(5, 5),"equal");
    }
}
