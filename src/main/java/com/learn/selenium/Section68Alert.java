package com.learn.selenium;

import com.learn.selenium.utils.ElementFinding;
import com.learn.selenium.utils.LinkWebPratice;
import com.learn.selenium.utils.TypeDriver;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.SpringApplication;

import java.time.Duration;

public class Section68Alert {
    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(Section68Alert.class, args);

        WebDriver driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();

        driver.get(LinkWebPratice.ALERT.getUrlLink());
        Thread.sleep(1000);

        String inputText = "U tối ôm trọn lấy bóng ma. Thiên tinh duy nhất hóa nhạt nhòa. Ngai trống vẫn chờ trong đất đá. Đế vương năm ấy trở về nhà.";
        // Nhập chuỗi vào input
        ElementFinding.elementValue(driver, inputText, "//input[@id='name']", TypeDriver.XPATH);
        driver.findElement(By.xpath("//input[@id='alertbtn']")).click();

        // Chuyển đổi sang chế độ alert
        Alert alert = driver.switchTo().alert();
        String msg = "Hello " + inputText + ", share this practice page and share your knowledge";
        System.out.println("============>>>>>>>>Alert Text: " + alert.getText());
        if (msg.equals(alert.getText())) {
            // Nhấn OK để chấp nhận alert
            alert.accept();
        }


        driver.findElement(By.xpath("//input[@id='confirmbtn']")).click();
        Alert confirmAlert = driver.switchTo().alert();
        // Nhấn Cancel để đóng confirm alert
        confirmAlert.dismiss();


        Thread.sleep(5000);
///*		driver.close();
//        driver.quit();
    }

}
