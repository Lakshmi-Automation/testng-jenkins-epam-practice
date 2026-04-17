package com.epam.testNG;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class Base {
    WebDriver driver;
    @BeforeClass
    public void setUp(){
        ChromeOptions options = new ChromeOptions();

        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        driver=new ChromeDriver(options);
        driver.manage().window().maximize();

    }

    @AfterClass
    public void tearDown(){
        if (driver!= null){
            driver.quit();
        }
    }
}