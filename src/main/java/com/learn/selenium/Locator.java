package com.learn.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Duration;

@SpringBootApplication
public class Locator {

	public static void main(String[] args) {
		SpringApplication.run(Locator.class, args);

		WebDriver driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		driver.get("https://rahulshettyacademy.com/locatorspractice");

		// Nhập email vào hộp thoại
		driver.findElement(By.id("inputUsername")).sendKeys("rahul");

		// Nhập mật khẩu vào hộp thoại
		driver.findElement(By.name("inputPassword")).sendKeys("hello123");

		// Nhấp vào nút đăng nhập
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		System.out.println(driver.findElement(By.xpath("//p[@class='error']")).getText());
		driver.findElement(By.linkText("Forgot your password?")).click();

		// Đóng trình duyệt

		// Đóng cửa sổ trình duyệt hiện tại
		driver.close();
	}

}
