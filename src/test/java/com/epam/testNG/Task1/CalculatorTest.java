package com.epam.testNG.Task1;

import org.testng.Assert;
import org.testng.annotations.*;

@Listeners(CustomListener.class)
public class CalculatorTest {

    private ThreadLocal<Calculator> calculator = ThreadLocal.withInitial(Calculator::new);

    @BeforeMethod
    public void setUp() {
        System.out.println("BeforeMethod running in thread: " + Thread.currentThread().getName());
        // Ensure calculator is initialized for this thread
        if (calculator.get() == null) {
            calculator.set(new Calculator());
        }
    }

    @AfterMethod
    public void tearDown() {
        calculator.remove();
    }

    private Calculator getCalculator() {
        Calculator calc = calculator.get();
        if (calc == null) {
            calc = new Calculator();
            calculator.set(calc);
        }
        return calc;
    }

    @Test(groups = "positive")
    public void testAddPositive() {
        Assert.assertEquals(getCalculator().add(2, 3), 5);
    }

    @Test(groups = "positive")
    public void testSubtractPositive() {
        Assert.assertEquals(getCalculator().subtract(5, 3), 2);
    }

    @Test(groups = "positive")
    public void testMultiplyPositive() {
        Assert.assertEquals(getCalculator().multiply(2, 3), 6);
    }

    @Test(groups = "positive")
    public void testDividePositive() {
        Assert.assertEquals(getCalculator().divide(6, 3), 2);
    }

    @Test(groups = "positive")
    public void testPowerPositive() {
        Assert.assertEquals(getCalculator().power(2, 3), 8);
    }

    // Negative tests
    @Test(groups = "negative", expectedExceptions = ArithmeticException.class)
    public void testDivideByZero() {
        getCalculator().divide(5, 0);
    }

    @Test(groups = "negative")
    public void testAddNegative() {
        Assert.assertNotEquals(getCalculator().add(2, 2), 5);
    }

    @Test(groups = "negative")
    public void testSubtractNegative() {
        Assert.assertNotEquals(getCalculator().subtract(5, 3), 1);
    }

    @Test(groups = "negative")
    public void testMultiplyNegative() {
        Assert.assertNotEquals(getCalculator().multiply(2, 3), 5);
    }

    @Test(groups = "negative")
    public void testPowerNegative() {
        Assert.assertNotEquals(getCalculator().power(2, 3), 7);
    }

    // Parameterized test
    @Test(dataProvider = "addDataProvider")
    public void testAddParameterized(int a, int b, int expected) {
        Assert.assertEquals(getCalculator().add(a, b), expected);
    }

    @DataProvider(name = "addDataProvider")
    public Object[][] addDataProvider() {
        return new Object[][] {
                {1, 2, 3},
                {0, 0, 0},
                {-1, 1, 0},
                {100, 200, 300}
        };
    }
}
