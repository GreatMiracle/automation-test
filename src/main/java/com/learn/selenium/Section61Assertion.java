package com.learn.selenium;

import com.learn.selenium.utils.LinkWebPratice;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.SpringApplication;
import org.springframework.util.Assert;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class Section61Assertion {

    public static void main(String[] args) throws InterruptedException {

        SpringApplication.run(Section7.class, args);

        WebDriver driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        driver.get(LinkWebPratice.ASSERTIONS.getUrlLink());
        Thread.sleep(1000);

//        Assert.assertFalse(driver.findElement(By.cssSelector("input[id*='SeniorCitizenDiscount']")).isSelected());

//Assert.assertFalse(true);System.out.println(driver.findElement(By.cssSelector("input[id*='SeniorCitizenDiscount']")).isSelected());

//        driver.findElement(By.cssSelector("input[id*='SeniorCitizenDiscount']")).click();

//        System.out.println(driver.findElement(By.cssSelector("input[id*='SeniorCitizenDiscount']")).isSelected());

//        Assert.assertTrue(driver.findElement(By.cssSelector("input[id*='SeniorCitizenDiscount']")).isSelected());

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.cssSelector("div[class='css-1dbjc4n r-18u37iz'] div[class='css-1dbjc4n r-14lw9ot r-11u4nky r-z2wwpe r-1phboty r-rs99b7 r-1loqt21 r-13awgt0 r-ymttw5 r-5njf8e r-1otgn73']")).click();
        WebElement nextMonth = driver.findElement(By.cssSelector("div[class='css-1dbjc4n r-1loqt21 r-u8s1d r-11xbo3g r-1v2oles r-1otgn73 r-16zfatd r-eafdt9 r-1i6wzkk r-lrvibr r-184en5c'] svg g circle"));

        LocalDate desiredDate = LocalDate.of(2025, 1, 1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
//        driver.findElement(By.xpath("//div[@class='css-76zvg2 r-homxoj r-adyw6z r-1kfrs79'][normalize-space()='January 2025']"));

        String inputMonth = desiredDate.format(formatter);

        WebElement monthCalendar;
        String currentMonthYear;
        monthCalendar = driver.findElement(By.xpath("//div[@class='css-76zvg2 r-homxoj r-adyw6z r-1kfrs79'][normalize-space()='"+inputMonth+"']"));
        // Chọn tháng và năm mong muốn
        do {

            Thread.sleep(1000);
            WebElement cur = driver.findElement(By.xpath("//div[@class='css-76zvg2 r-homxoj r-adyw6z r-1kfrs79']"));

            currentMonthYear = cur.getText().trim();
            nextMonth.click();
        } while (!currentMonthYear.equals(monthCalendar.getText()));

        System.out.println("a");
    }
}
