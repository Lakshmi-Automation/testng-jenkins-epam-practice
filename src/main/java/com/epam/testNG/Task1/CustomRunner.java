package com.epam.testNG.Task1;

import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import java.util.*;

public class CustomRunner {
    public static void main(String[] args) {
        TestNG testng = new TestNG();
        List<String> suites = new ArrayList<>();
        suites.add("CalculatorTestng.xml"); // Use the correct path to your xml file
        testng.setTestSuites(suites);
        testng.run();
    }
}