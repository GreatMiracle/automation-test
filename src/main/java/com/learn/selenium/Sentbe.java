package com.learn.selenium;

import com.learn.selenium.utils.ElementFinding;
import com.learn.selenium.utils.LinkWebPratice;
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

        driver.get(LinkWebPratice.SENT_BE.getUrlLink());

        // CSS Selector
        Thread.sleep(1000);
        ElementFinding.elementValue(driver, userNameFalse, "#username", TypeDriver.CSSSELECTOR);
        ElementFinding.elementValue(driver, pwdFalse, "#password", TypeDriver.CSSSELECTOR);

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
            ElementFinding.elementValue(driver, userNameTrue, "#username", TypeDriver.CSSSELECTOR);

            Thread.sleep(200);
            driver.findElement(By.cssSelector("#password")).clear();
            ElementFinding.elementValue(driver, pwdTrue, "#password", TypeDriver.CSSSELECTOR);

            Thread.sleep(500);
            driver.findElement(By.cssSelector("button[type='submit']")).click();
        }

/*		driver.close();
		driver.quit();*/
    }

}
