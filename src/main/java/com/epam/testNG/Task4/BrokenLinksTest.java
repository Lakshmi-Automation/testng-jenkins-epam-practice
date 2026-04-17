package com.epam.testNG.Task4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BrokenLinksTest {

    @Test
    public void checkBrokenLink() {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://demoqa.com/broken");

        String linkText = driver.findElement(By.linkText("Click Here for Broken Link")).getText();

        Assert.assertEquals(linkText, "Click Here for Broken Link");

        driver.quit();
    }

    @Test
    public void checkValidLink() {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://demoqa.com/broken");

        String linkText = driver.findElement(By.linkText("Click Here for Valid Link")).getText();

        Assert.assertEquals(linkText, "Click Here for Valid Link");

        driver.quit();
    }
}