package com.learn.selenium;

import com.learn.selenium.utils.TypeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v121.page.Page;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Duration;

import static java.lang.Thread.sleep;

@SpringBootApplication
public class Locator {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(Locator.class, args);

        String userName = "rahul";
        String pwd = "hello123";

        WebDriver driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        driver.get("https://rahulshettyacademy.com/locatorspractice");

        // CSS Selector
		Thread.sleep(1000);
//		driver.findElement(By.id("inputUsername")).sendKeys(userName);
//		driver.findElement(By.name("inputPassword")).sendKeys(pwd);
        inputValue(driver, userName, "inputUsername", TypeDriver.ID);
        inputValue(driver, pwd, "inputPassword", TypeDriver.NAME);

        Thread.sleep(500);
//		driver.findElement(By.cssSelector("button[type='submit']")).click();
        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(submitButton).click().perform();

        if (driver.findElement(By.cssSelector(".error.error")).getText().equals("* Incorrect username or password")) {
            driver.findElement(By.linkText("Forgot your password?")).click();
			Thread.sleep(1000);
            inputValue(driver, "John", "input[placeholder='Name']", TypeDriver.CSSSELECTOR);

			Thread.sleep(200);
            inputValue(driver, "john@rsa.com", "input[placeholder='Email']", TypeDriver.CSSSELECTOR);

            Thread.sleep(500);
            driver.findElement(By.cssSelector("input[placeholder='Email']")).clear();

            Thread.sleep(200);
            inputValue(driver, "john@gmail.com", "input[placeholder='Email']", TypeDriver.CSSSELECTOR);

			Thread.sleep(200);
            inputValue(driver, "0123456789", "input[placeholder='Phone Number']", TypeDriver.CSSSELECTOR);

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
            inputValue(driver, "rahul", "inputUsername", TypeDriver.ID);
            inputValue(driver, "rahulshettyacademy", "inputPassword", TypeDriver.NAME);

            Thread.sleep(500);
            driver.findElement(By.cssSelector("button[type='submit']")).click();

        }

        // Đóng cửa sổ trình duyệt hiện tại
//		driver.close();

		// Đóng trình duyệt
//		driver.quit();
	}

    private static void inputValue(WebDriver driver, String value, String cssSyntax, TypeDriver type) {
        WebElement driverField;
        switch (type) {
            case ID -> driverField = driver.findElement(By.id(cssSyntax));
            case CSSSELECTOR -> driverField = driver.findElement(By.cssSelector(cssSyntax));
            case XPATH -> driverField = driver.findElement(By.xpath(cssSyntax));
            case NAME -> driverField = driver.findElement(By.name(cssSyntax));
            default -> {
                throw new IllegalArgumentException("Unsupported locator type: " + type);
            }
        }

        for (char c : value.toCharArray()) {
            driverField.sendKeys(String.valueOf(c));
            try {
                sleep(150); // Tạm dừng 150 milliseconds giữa các ký tự
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
