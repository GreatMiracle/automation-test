package com.learn.selenium;

import com.learn.selenium.utils.LinkWebPratice;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Duration;

@SpringBootApplication
public class Section7 {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(Section7.class, args);

        WebDriver driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();

        driver.get(LinkWebPratice.DROPDOWNS.getUrlLink());
        Thread.sleep(1000);

        WebElement dropdownCurrency = driver.findElement(By.cssSelector("#ctl00_mainContent_DropDownListCurrency"));
        dropdownCurrency.click();
        Thread.sleep(500);

        Select dropdown = new Select(dropdownCurrency);
        dropdown.selectByIndex(2);

//        dropdown.selectByVisibleText("INR");
//        dropdown.selectByValue("INR");

        System.out.println(dropdown.getFirstSelectedOption().getText());

        /**
         * Các phương pháp chọn tùy chọn trong dropdown:
         *     selectByIndex(int index): Chọn tùy chọn theo chỉ số. Ví dụ, chỉ số 0 là tùy chọn đầu tiên.
         *     selectByVisibleText(String text): Chọn tùy chọn theo văn bản hiển thị.
         *     selectByValue(String value): Chọn tùy chọn theo giá trị thuộc tính value của thẻ <option>.
         *
         */

/*		driver.close();
		driver.quit();*/
    }

}