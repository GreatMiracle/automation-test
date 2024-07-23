package com.learn.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SeleniumApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeleniumApplication.class, args);
//		System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
//		System.setProperty("webdriver.gecko.driver", "/path/to/geckodriver"); //Firefox
//		System.setProperty("webdriver.edge.driver", "/path/to/msedgedriver"); //Edge

//		WebDriver driver = new ChromeDriver();
		WebDriver driver = new FirefoxDriver();
//		WebDriver driver = new EdgeDriver();

		// Mở trang Google
//		driver.get("https://www.google.com");

		driver.get("https://rahulshettyacademy.com");

		// Lấy và hiển thị tiêu đề của trang
		String title = driver.getTitle();
		System.out.println("Tiêu đề trang: " + title);

		// Lấy và hiển thị URL hiện tại
		String currentUrl = driver.getCurrentUrl();
		System.out.println("URL hiện tại: " + currentUrl);

		// Đóng cửa sổ trình duyệt hiện tại
//		driver.close();
	}

}
