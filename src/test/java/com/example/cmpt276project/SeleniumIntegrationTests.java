package com.example.cmpt276project;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class SeleniumIntegrationTests {

    private String domainUri = "https://roomlink.onrender.com";

    private String firstName = "Cheuk Lam";
    private String lastName = "Chan";
    private String nickname = "Jan";
    private String gender = "F";
    private String email = "123456@123456.com";
    private String password = "123456";

    int waitTime = 1000;

    @Test
    @Order(1)
    public void signupSucceeded() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        String currentUri = domainUri + "/";
        driver.get(currentUri);
        Thread.sleep(waitTime);

        // Main page
        assert(currentUri.equals(driver.getCurrentUrl()));
        WebElement signupButton = driver.findElement(By.xpath("//*[@id=\"sign-up\"]"));
        WebElement loginButton = driver.findElement(By.xpath("//*[@id=\"log-in\"]"));
        signupButton.click();
        Thread.sleep(waitTime);

        // Choose user page
        currentUri = domainUri + "/chooseuser.html";
        assert(currentUri.equals(driver.getCurrentUrl()));
        WebElement landlordButton = driver.findElement(By.xpath("/html/body/div[3]/figure[1]/a/img"));
        WebElement studentButton = driver.findElement(By.xpath("/html/body/div[3]/figure[2]/a/img"));
        landlordButton.click();
        Thread.sleep(waitTime);

        // Add user landlord page
        currentUri = domainUri + "/adduserLandlord.html";
        assert(currentUri.equals(driver.getCurrentUrl()));
        WebElement firstNameEdittext = driver.findElement(By.xpath("//*[@id=\"first\"]"));
        firstNameEdittext.sendKeys(firstName);
        WebElement lastNameEdittext = driver.findElement(By.xpath("//*[@id=\"last\"]"));
        lastNameEdittext.sendKeys(lastName);
        WebElement nicknameEdittext = driver.findElement(By.xpath("//*[@id=\"nick\"]"));
        nicknameEdittext.sendKeys(nickname);
        WebElement genderEdittext = driver.findElement(By.xpath("//*[@id=\"gender\"]"));
        genderEdittext.sendKeys(gender);
        WebElement emailEdittext = driver.findElement(By.xpath("//*[@id=\"email\"]"));
        emailEdittext.sendKeys(email);
        WebElement passwordEdittext = driver.findElement(By.xpath("//*[@id=\"password\"]"));
        passwordEdittext.sendKeys(password);
        WebElement createButton = driver.findElement(By.xpath("/html/body/main/form/div[7]/input"));
        createButton.submit();
        Thread.sleep(waitTime);

        // Add user landlord page
        currentUri = domainUri + "/user/adduserLandlord";
        assert(currentUri.equals(driver.getCurrentUrl()));
        WebElement messageLabel = driver.findElement(By.xpath("/html/body/p"));
        assert("New Record Successfully Created".equals(messageLabel.getText()));
        Thread.sleep(waitTime);

        driver.close();
        Thread.sleep(waitTime);
    }

//    @Test
//    @Order(2)
//    public void signupFailed() throws InterruptedException {
//        WebDriver driver = new ChromeDriver();
//        String currentUri = domainUri + "/";
//        driver.get(currentUri);
//        Thread.sleep(waitTime);
//
//        // Main page
//        assert(currentUri.equals(driver.getCurrentUrl()));
//        WebElement signupButton = driver.findElement(By.xpath("//*[@id=\"sign-up\"]"));
//        WebElement loginButton = driver.findElement(By.xpath("//*[@id=\"log-in\"]"));
//        signupButton.click();
//        Thread.sleep(waitTime);
//
//        // Choose user page
//        currentUri = domainUri + "/chooseuser.html";
//        assert(currentUri.equals(driver.getCurrentUrl()));
//        WebElement landlordButton = driver.findElement(By.xpath("/html/body/div[3]/figure[1]/a/img"));
//        WebElement studentButton = driver.findElement(By.xpath("/html/body/div[3]/figure[2]/a/img"));
//        landlordButton.click();
//        Thread.sleep(waitTime);
//
//        // Add user landlord page
//        currentUri = domainUri + "/adduserLandlord.html";
//        assert(currentUri.equals(driver.getCurrentUrl()));
//        WebElement firstNameEdittext = driver.findElement(By.xpath("//*[@id=\"first\"]"));
//        firstNameEdittext.sendKeys(firstName);
//        WebElement lastNameEdittext = driver.findElement(By.xpath("//*[@id=\"last\"]"));
//        lastNameEdittext.sendKeys(lastName);
//        WebElement nicknameEdittext = driver.findElement(By.xpath("//*[@id=\"nick\"]"));
//        nicknameEdittext.sendKeys(nickname);
//        WebElement genderEdittext = driver.findElement(By.xpath("//*[@id=\"gender\"]"));
//        genderEdittext.sendKeys(gender);
//        WebElement emailEdittext = driver.findElement(By.xpath("//*[@id=\"email\"]"));
//        emailEdittext.sendKeys(email);
//        WebElement passwordEdittext = driver.findElement(By.xpath("//*[@id=\"password\"]"));
//        passwordEdittext.sendKeys(password);
//        WebElement createButton = driver.findElement(By.xpath("/html/body/main/form/div[7]/input"));
//        createButton.submit();
//        Thread.sleep(waitTime);
//
//        // Add user landlord page
//        currentUri = domainUri + "/user/adduserLandlord";
//        assert(currentUri.equals(driver.getCurrentUrl()));
//        WebElement messageLabel = driver.findElement(By.xpath("/html/body/p"));
//        assert("This email is used".equals(messageLabel.getText()));
//        Thread.sleep(waitTime);
//
//        driver.close();
//        Thread.sleep(waitTime);
//    }
}
