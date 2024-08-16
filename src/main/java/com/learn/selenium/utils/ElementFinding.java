package com.learn.selenium.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static java.lang.Thread.sleep;

public class ElementFinding {
    public static void elementValue(WebDriver driver, String value, String cssSyntax, TypeDriver type) {
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
