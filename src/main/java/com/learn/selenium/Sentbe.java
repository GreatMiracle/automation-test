package com.learn.selenium;

import com.learn.selenium.utils.TypeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Duration;

import static java.lang.Thread.sleep;

@SpringBootApplication
public class Sentbe {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(Sentbe.class, args);

        String userNameTrue = "user";
        String pwdTrue = "admin1234567";

        String userNameFalse = "useraaaaaaaaaaaaaa";
        String pwdFalse = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";


        WebDriver driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://autofx-fe-dev.sentbe.net/login");

        // CSS Selector
		Thread.sleep(1000);
        inputValue(driver, userNameFalse, "#username", TypeDriver.CSSSELECTOR);
        inputValue(driver, pwdFalse, "#password", TypeDriver.CSSSELECTOR);

        Thread.sleep(500);
        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(submitButton).click().perform();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Thời gian chờ tối đa là 5 giây

        // Chờ cho phần tử .infoMsg xuất hiện
        WebElement infoMsgElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='text-red text-sm']")));
        String infoMsgResetLogin = infoMsgElement.getText();

        if (infoMsgResetLogin.equals("account not existed")) {

			Thread.sleep(1000);
            driver.findElement(By.cssSelector("#username")).clear();
            inputValue(driver, userNameTrue, "#username", TypeDriver.CSSSELECTOR);

			Thread.sleep(200);
            driver.findElement(By.cssSelector("#password")).clear();
            inputValue(driver, pwdTrue, "#password", TypeDriver.CSSSELECTOR);

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
