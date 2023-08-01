package com.example.cmpt276project;

import com.example.cmpt276project.models.User;
import com.example.cmpt276project.models.UserRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(SpringExtension.class)
public class SeleniumIntegrationTests {

    @Autowired
    private UserRepository userRepository;

    private WebDriver driver;

    private String domainUri = "https://roomlink.onrender.com";

    private String firstName = "Cheuk Lam";
    private String lastName = "Chan";
    private String nickname = "Jan";
    private String gender = "F";
    private String email = "123456@123456.com";
    private String password = "123456";

    int waitTime = 1000;

    @BeforeEach
    public void before() {
        driver = new ChromeDriver();
    }

    @AfterEach
    public void after() {
        driver.quit();
    }

    @Test
    @Order(1)
    public void deleteTestUser() {
        List<User> users = userRepository.findByEmail(email);
        if (!users.isEmpty()) {
            userRepository.deleteAll(users);
        }
    }

    @Test
    @Order(2)
    public void signupAddLandlordAccountSucceeded() throws InterruptedException {
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
    }

    @Test
    @Order(3)
    public void signupAddLandlordAccountFailed() throws InterruptedException {
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
        assert("This email is used".equals(messageLabel.getText()));
        Thread.sleep(waitTime);
    }

    @Test
    @Order(4)
    public void loginAndLogoutLandlordAccount() throws InterruptedException {
        String currentUri = domainUri + "/";
        driver.get(currentUri);
        Thread.sleep(waitTime);

        // Main page
        assert(currentUri.equals(driver.getCurrentUrl()));
        WebElement signupButton = driver.findElement(By.xpath("//*[@id=\"sign-up\"]"));
        WebElement loginButton = driver.findElement(By.xpath("//*[@id=\"log-in\"]"));
        loginButton.click();
        Thread.sleep(waitTime);

        // Login page
        currentUri = domainUri + "/login";
        assert(currentUri.equals(driver.getCurrentUrl()));
        WebElement emailEdittext = driver.findElement(By.xpath("//*[@id=\"email\"]"));
        emailEdittext.sendKeys(email);
        WebElement passwordEdittext = driver.findElement(By.xpath("//*[@id=\"password\"]"));
        passwordEdittext.sendKeys(password);
        WebElement loginButton2 = driver.findElement(By.xpath("/html/body/form/fieldset/a/input"));
        loginButton2.submit();
        Thread.sleep(waitTime);

        // Main page
        currentUri = domainUri + "/";
        assert(currentUri.equals(driver.getCurrentUrl()));
        WebElement profileButton = driver.findElement(By.xpath("//*[@id=\"nav\"]/div/div[1]/img"));
        profileButton.click();
        WebElement logoutButton = driver.findElement(By.xpath("//*[@id=\"nav\"]/div/div[2]/ul/li[3]/a"));
        logoutButton.click();
        Thread.sleep(waitTime);

        // Main page
        currentUri = domainUri + "/";
        assert(currentUri.equals(driver.getCurrentUrl()));
        Thread.sleep(waitTime);
    }
}
