package com.epam.testNG;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import java.time.Duration;

public class DependencyTest extends Base {
    WebDriverWait wait;
    JavascriptExecutor js;
    @BeforeClass
    public void setUp() {
        super.setUp();
        wait= new WebDriverWait(driver, Duration.ofSeconds(2000));
        js = (JavascriptExecutor) driver;
        driver.get("https://demoqa.com/webtables");
    }

    @Test
    public void pageTitle() {
        String pageTitle = driver.getTitle();
        Assert.assertEquals(pageTitle, "DEMOQA");
    }

    @Test
    public void addNewRecord() {
        WebElement addButton = driver.findElement(By.id("addNewRecordButton"));
        js.executeScript("arguments[0].scrollIntoView(true);", addButton);
        addButton.click();
        driver.findElement(By.id("firstName")).sendKeys("John");
        driver.findElement(By.id("lastName")).sendKeys("Doe");
        driver.findElement(By.id("userEmail")).sendKeys("johndoe.@example.com");
        driver.findElement(By.id("age")).sendKeys("30");
        driver.findElement(By.id("salary")).sendKeys("50000");
        driver.findElement(By.id("department")).sendKeys("Engineering");
        driver.findElement(By.id("submit")).click();
    }
    @Test(dependsOnMethods = "addNewRecord")
    public void verifyNewRecord() {
        WebElement firstName = driver.findElement(By.xpath("//div[@class='rt-tbody']//div[contains(text(),'John')]"));
        Assert.assertEquals(firstName.getText(), "John");
        WebElement lastName = driver.findElement(By.xpath("//div[@class='rt-tbody']//div[contains(text(),'Doe')]"));
        Assert.assertEquals(lastName.getText(), "Doe");
        WebElement email = driver.findElement(By.xpath("//div[@class='rt-tbody']//div[contains(text(),'johndoe.@example.com')]"));
        Assert.assertEquals(email.getText(), "johndoe.@example.com");
        WebElement age = driver.findElement(By.xpath("//div[@class='rt-tbody']//div[contains(text(),'30')]"));
        Assert.assertEquals(age.getText(), "30");
        WebElement salary = driver.findElement(By.xpath("//div[@class='rt-tbody']//div[contains(text(),'50000')]"));
        Assert.assertEquals(salary.getText(), "50000");
        WebElement department = driver.findElement(By.xpath("//div[@class='rt-tbody']//div[contains(text(),'Engineering')]"));
        Assert.assertEquals(department.getText(), "Engineering");
    }
    @AfterClass
    public void tearDown(){
        super.tearDown();
    }
}