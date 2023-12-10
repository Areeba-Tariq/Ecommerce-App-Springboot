package com.jtspringproject.JtSpringProject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertTrue;

public class LoginTestSelenium {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\hp\\Desktop\\SQE\\Project\\E-commerce-project-springBoot\\E-commerce-project-springBoot\\JtProject\\src\\test\\java\\com\\jtspringproject\\JtSpringProject\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://localhost:8080/");
    }

    @Test
    public void testLogin() {
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.cssSelector("input[type='submit']"));
        usernameField.sendKeys("lisa");
        passwordField.sendKeys("765");
        loginButton.click();
        String pageContent = driver.getPageSource();
        assertTrue("Page should contain 'Profile'", pageContent.contains("Profile"));
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
