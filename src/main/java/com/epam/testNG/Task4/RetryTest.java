package com.epam.testNG.Task4;


import org.testng.Assert;
import org.testng.annotations.Test;

public class RetryTest {

    // Test 1: Always Pass
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void test1_Pass() {
        System.out.println("Executing Test 1");
        Assert.assertTrue(true);
    }

    // Test 2: Always Fail
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void test2_Fail() {
        System.out.println("Executing Test 2");
        Assert.assertTrue(false);
    }

    // Test 3: Fail First Time, Pass on Retry
    private static int count = 0;

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void test3_PassOnRetry() {

        System.out.println("Executing Test 3");

        if (count == 0) {
            count++;
            Assert.fail("Failing First Time");
        }

        Assert.assertTrue(true);
    }
}