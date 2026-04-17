package com.epam.testNG.Task4;


import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    private int currentRetryCount = 0;
    private static final int maxRetryCount = 1; // Retry only once

    @Override
    public boolean retry(ITestResult result) {

        if (currentRetryCount < maxRetryCount) {
            currentRetryCount++;
            System.out.println("Retrying Test: " + result.getName());
            return true; // Re-run test
        }
        return false; // Do not retry
    }
}