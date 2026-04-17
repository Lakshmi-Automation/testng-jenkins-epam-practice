package com.epam.testNG;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import java.time.Duration;

public class DataProviderTest{
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static ThreadLocal<WebDriverWait> wait = new ThreadLocal<>();

    @BeforeMethod
    public void setUp(){
        WebDriver webDriver = new ChromeDriver();
        driver.set(webDriver);
        wait.set(new WebDriverWait(webDriver, Duration.ofSeconds(10)));
        getDriver().manage().window().maximize();
        getDriver().get("https://the-internet.herokuapp.com/login");
    }

    public WebDriver getDriver() {
        return driver.get();
    }

    public WebDriverWait getWait() {
        return wait.get();
    }

    @DataProvider(name="loginData", parallel = true)
    public Object[][] loginData(){
        return new Object[][]{
                {"tomsmith", "SuperSecretPassword!", true},
                {"invalidUser", "invalidPass", false},
        };
    }

    @Test(dataProvider = "loginData")
    public void loginTest(String username, String password, boolean isValid) {
        getDriver().findElement(By.id("username")).sendKeys(username);
        getDriver().findElement(By.id("password")).sendKeys(password);
        getDriver().findElement(By.xpath("//button[@type='submit']")).click();

        WebElement message = getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("flash")));
        String messageText = message.getText();

        if (isValid) {
            Assert.assertTrue(messageText.contains("You logged into a secure area!"), "Expected success message not found.");
        } else {
            Assert.assertTrue(messageText.contains("Your username is invalid!") || messageText.contains("Your password is invalid!"), "Expected error message not found.");
        }
    }

    @AfterMethod
    public void tearDown(){
        if (getDriver() != null){
            getDriver().quit();
            driver.remove();
            wait.remove();
        }
    }
}
