package com.learn.selenium;

import com.learn.selenium.utils.ElementFinding;
import com.learn.selenium.utils.LinkWebPratice;
import com.learn.selenium.utils.TypeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

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
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("#divpaxinfo")).click();

        Thread.sleep(1000);

        int child = (int)(Math.random() * 5);
        int infant = (int)(Math.random() * 5);
        int adult = (int)(Math.random() * (9-child));

        for (int i = 1; i < adult; i++) {
            Thread.sleep(500);
            driver.findElement(By.cssSelector("#hrefIncAdt")).click();

        }

        Thread.sleep(1000);
        for (int i = 1; i < child; i++) {
            Thread.sleep(500);
            driver.findElement(By.cssSelector("#hrefIncChd")).click();
        }

        Thread.sleep(1000);
        for (int i = 1; i < infant; i++) {
            Thread.sleep(500);
            driver.findElement(By.cssSelector("#hrefIncInf")).click();
        }

        Thread.sleep(1000);
        driver.findElement(By.cssSelector("#btnclosepaxoption")).click();

        //DropDown From-To
        Thread.sleep(500);
        WebElement fromDefaultElement= driver.findElement(By.cssSelector("#ctl00_mainContent_ddl_originStation1_CTXT"));
        fromDefaultElement.click();

        List<WebElement> optionsFrom = driver.findElements(By.xpath("//div[@id='glsctl00_mainContent_ddl_originStation1_CTNR']//ul//a"));

        int totalOptionsFrom = optionsFrom.size();
        System.out.println("Number of options FROM in dropdown: " + totalOptionsFrom);

        Thread.sleep(1000);
        AtomicInteger indexSelected = new AtomicInteger(0);
        selectRandomIndex(driver, optionsFrom, totalOptionsFrom, indexSelected);
        System.out.println("indexSelected of FROM ::::::::::::::::::::" + indexSelected);

        Thread.sleep(1000);
        WebElement toefaultElement= driver.findElement(By.cssSelector("#ctl00_mainContent_ddl_destinationStation1_CTXT"));
        toefaultElement.click();

        List<WebElement> optionsTo = driver.findElements(By.xpath("//div[@id='glsctl00_mainContent_ddl_destinationStation1_CTNR']//ul//a"));
        Thread.sleep(1000);
        AtomicInteger indexToSelected = new AtomicInteger(0);
        int totalOptionsTo = optionsTo.size();
        System.out.println("Number of options TO in dropdown: " + totalOptionsTo);
        selectRandomIndex(driver, optionsTo, totalOptionsTo, indexToSelected);
        System.out.println("indexSelected of TO ::::::::::::::::::::" + indexToSelected);

        Thread.sleep(1000);
        ElementFinding.elementValue(driver, "th", "#autosuggest", TypeDriver.CSSSELECTOR);

        Thread.sleep(1500);
        List<WebElement> optionsCountry = driver.findElements(By.xpath("//ul[@id='ui-id-1']//a"));
        AtomicInteger inputCountrySelected = new AtomicInteger(0);
        int totalOptionsCountry = optionsCountry.size();
        System.out.println("Number of options COUNTRY in dropdown: " + totalOptionsCountry);
        selectRandomIndex(driver, optionsCountry, totalOptionsCountry, inputCountrySelected);
        System.out.println("indexSelected of COUNTRY ::::::::::::::::::::" + inputCountrySelected);

        Thread.sleep(1000);
        radioAndCheckbox(driver, "ctl00$mainContent$rbtnl_Trip", TypeDriver.NAME);

        Thread.sleep(1000);
        radioAndCheckbox(driver, "//div[@id='discount-checkbox']//input", TypeDriver.XPATH);

        Thread.sleep(5000);
///*		driver.close();
		driver.quit();
    }

    private static void radioAndCheckbox(WebDriver driver, String elementStr, TypeDriver typeDriver) {
        List<WebElement> tick = new ArrayList<>();
        if(typeDriver.equals(TypeDriver.XPATH)){
            tick = driver.findElements(By.xpath(elementStr));
        }

        if (typeDriver.equals(TypeDriver.NAME)){
            tick = driver.findElements(By.name(elementStr));
        }

        if (!tick.isEmpty()) {
            Random random = new Random();
            int totalOfTick = tick.size();
            int randomIndex = random.nextInt(totalOfTick);

            WebElement tickButton;
            if (typeDriver.equals(TypeDriver.NAME)){
                tickButton = tick.get(0);
            }else {
                tickButton = tick.get(randomIndex);
            }
            tickButton.click();
            System.out.println("Đã chọn radio button có chỉ số: " + randomIndex);

        } else {
            System.out.println("Không tìm thấy radio button nào với tên 'ctl00$mainContent$rbtnl_Trip'");
        }
    }

    public static void selectRandomIndex(WebDriver dropdownFrom, List<WebElement> options, int totalOptions, AtomicInteger  indexSelected) {
        Random random = new Random();

        // Sinh ra một số ngẫu nhiên trong khoảng từ 0 đến totalOptions - 1
        int randomIndex = random.nextInt(totalOptions);
        indexSelected.set(randomIndex);

        System.out.println("Chọn chỉ số ngẫu nhiên: " + indexSelected);
        System.out.println("Phần tử được chọn: " + options.get(randomIndex).getText());
        // Click vào phần tử được chọn dựa trên chỉ số ngẫu nhiên
        options.get(randomIndex).click();

    }


}
