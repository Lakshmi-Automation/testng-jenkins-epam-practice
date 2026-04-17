package com.epam.testNG;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Task2Annotations extends Base {
    WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(2000));
    @BeforeClass
    public void setUp(){
        super.setUp();
        driver.get("https://google.com");
    }

    @Test
    public void testMethod1(){
        String expectedTitle = "Google";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle);
    }
    @Test
    public void testMethod2(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String readyState =js.executeScript("return document.readyState").toString();
        Assert.assertEquals(readyState, "complete");
    }
    @AfterClass
    public void tearDown(){
        super.tearDown();
    }


}