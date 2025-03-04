package com.learn.selenium;

import com.learn.selenium.utils.ElementFinding;
import com.learn.selenium.utils.LinkWebPratice;
import com.learn.selenium.utils.MousePointerUI;
import com.learn.selenium.utils.TypeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Duration;

@SpringBootApplication
public class Locator {

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("java.awt.headless", "false");
        SpringApplication.run(Locator.class, args);

        String userName = "rahul";
        String pwd = "hello123";

        // Tắt chế độ headless cho Firefox
        FirefoxOptions options = new FirefoxOptions();

        WebDriver driver = new FirefoxDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.get(LinkWebPratice.LOCATORS.getUrlLink());

        // CSS Selector
        Thread.sleep(1000);
//		driver.findElement(By.id("inputUsername")).sendKeys(userName);
//		driver.findElement(By.name("inputPassword")).sendKeys(pwd);
        ElementFinding.elementValue(driver, userName, "inputUsername", TypeDriver.ID);
        ElementFinding.elementValue(driver, pwd, "inputPassword", TypeDriver.NAME);

        Thread.sleep(500);
//		driver.findElement(By.cssSelector("button[type='submit']")).click();
        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));
        Actions actions = new Actions(driver);
        MousePointerUI.moveMouseToElement(driver, submitButton);
        actions.moveToElement(submitButton).click().perform();

        if (driver.findElement(By.cssSelector(".error.error")).getText().equals("* Incorrect username or password")) {
            driver.findElement(By.linkText("Forgot your password?")).click();
            Thread.sleep(1000);
            ElementFinding.elementValue(driver, "John", "input[placeholder='Name']", TypeDriver.CSSSELECTOR);

            Thread.sleep(200);
            ElementFinding.elementValue(driver, "john@rsa.com", "input[placeholder='Email']", TypeDriver.CSSSELECTOR);

            Thread.sleep(500);
            driver.findElement(By.cssSelector("input[placeholder='Email']")).clear();

            Thread.sleep(200);
            ElementFinding.elementValue(driver, "john@gmail.com", "input[placeholder='Email']", TypeDriver.CSSSELECTOR);

            Thread.sleep(200);
            ElementFinding.elementValue(driver, "0123456789", "input[placeholder='Phone Number']", TypeDriver.CSSSELECTOR);

            Thread.sleep(500);
            driver.findElement(By.xpath("//button[normalize-space()='Reset Login']")).click();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5)); // Thời gian chờ tối đa là 5 giây

            // Chờ cho phần tử .infoMsg xuất hiện
            WebElement infoMsgElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='infoMsg']")));
            String infoMsgResetLogin = infoMsgElement.getText();
            if (infoMsgResetLogin.equals("Please use temporary password 'rahulshettyacademy' to Login.")) {
                Thread.sleep(500);
                driver.findElement(By.xpath("//button[normalize-space()='Go to Login']")).click();
            }

            Thread.sleep(1000);
            ElementFinding.elementValue(driver, "rahul", "inputUsername", TypeDriver.ID);
            ElementFinding.elementValue(driver, "rahulshettyacademy", "inputPassword", TypeDriver.NAME);

            Thread.sleep(500);
            driver.findElement(By.cssSelector("button[type='submit']")).click();


            WebDriverWait waitLoginSuccess = new WebDriverWait(driver, Duration.ofSeconds(5)); // Thời gian chờ tối đa là 5 giây

            // Chờ cho phần tử .infoMsg xuất hiện
            WebElement successMessage = waitLoginSuccess.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//p[normalize-space()='You are successfully logged in.']")
            ));
//            List<WebElement> successMessageElements = driver.findElements(
//                    By.xpath("//p[normalize-space()='You are successfully logged in.']"));

            if (successMessage != null) {
                // Login was successful, proceed to logout
                Thread.sleep(5000);
                WebElement logoutButton = driver.findElement(By.xpath("//button[normalize-space()='Log Out']"));
                logoutButton.click();
                System.out.println("Logged out successfully.");
            } else {
                System.out.println("Login failed or success message not found.");
            }
        }

        // Đóng cửa sổ trình duyệt hiện tại
        driver.close();

        // Đóng trình duyệt
//		driver.quit();
    }

}
