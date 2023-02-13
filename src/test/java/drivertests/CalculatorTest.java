package drivertests;

import driver.Calculator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CalculatorTest {

    Calculator calculator;

    @Before
    public void init() {
        calculator = new Calculator();
    }

    @Test
    public void testMultiply() {
        Assert.assertEquals(20, calculator.multiply(5, 4));
    }

    @Test
    public void testAdd() {
        Assert.assertEquals(15, calculator.add(10, 5));
    }
}
