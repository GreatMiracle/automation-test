package com.learn.selenium;

import com.learn.selenium.utils.LinkWebPratice;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Duration;
import java.util.List;

@SpringBootApplication
public class Section71AddToCart {
    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(Section71AddToCart.class, args);

        WebDriver driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.get(LinkWebPratice.SELENIUM.getUrlLink());

        Thread.sleep(1000);

        List<WebElement> products = driver.findElements(By.cssSelector("h4.product-name"));

        for (int i = 0; i < products.size(); i++) {
            String[] name = products.get(i).getText().split("-");
            String formattedName = name[0].trim();
            System.out.println(formattedName);
            if (formattedName.contains("Ca")) {
                int randomIncrement = (int) (Math.random() * 9);
                System.out.println("randomIncrement: " + randomIncrement);
                for (int j = 0; j < randomIncrement; j++) {
                    Thread.sleep(600);

                    WebElement incrementButton = null;
                    boolean elementFound = false;

                    // Sử dụng vòng lặp để cuộn cho đến khi tìm thấy phần tử và nó nằm trong khung nhìn
                    incrementButton = checkExistElementOnScreenAndScroll(driver, i, incrementButton, elementFound, "(//a[@class='increment'][normalize-space()='+'])[");

                    hoverAction(driver, incrementButton);
                    Thread.sleep(500);

                    incrementButton.click();
                    Thread.sleep(500);
                }

                if (randomIncrement > 2) {
                    Thread.sleep(1000);
                    int randomDecrement = (int) (Math.random() * (randomIncrement - 2));
                    System.out.println("randomDecrement: " + randomDecrement);
                    for (int j = 0; j < randomDecrement; j++) {
                        Thread.sleep(600);

                        WebElement decrementButton = null;
                        boolean elementFound = false;

                        // Sử dụng vòng lặp để cuộn cho đến khi tìm thấy phần tử và nó nằm trong khung nhìn
                        decrementButton = checkExistElementOnScreenAndScroll(driver, i, decrementButton, elementFound, "(//a[@class='decrement'][contains(text(),'–')])[");

                        hoverAction(driver, decrementButton);
                        Thread.sleep(500);

                        decrementButton.click();
                        Thread.sleep(500);
                    }
                }

                Thread.sleep(1000);
                WebElement addToCartButton = null;
                boolean elementFoundSubmit = false;
                addToCartButton = checkExistElementOnScreenAndScroll(driver, i, addToCartButton, elementFoundSubmit, "(//div[@class='product-action'])[");

                hoverAction(driver, addToCartButton);
                Thread.sleep(500);

                addToCartButton.click();
                Thread.sleep(500);
            }
        }

        int items = Integer.parseInt(driver.findElement(By.xpath("//tbody/tr[1]/td[3]/strong[1]")).getText());
        if (items>0){
            driver.findElement(By.cssSelector(".cart-icon")).click();
        }


        // Đóng cửa sổ trình duyệt hiện tại
        Thread.sleep(10000);
        driver.close();
    }

    private static WebElement checkExistElementOnScreenAndScroll(WebDriver driver, int i, WebElement incrementButton, boolean elementFound, String s) throws InterruptedException {
        while (!elementFound) {
            try {
                incrementButton = driver.findElement(By.xpath(s + (i + 1) + "]"));

                // Kiểm tra nếu phần tử nằm trong khung nhìn
                if (isElementInViewPort(driver, incrementButton)) {
                    elementFound = true; // Nếu tìm thấy phần tử và nó trong khung nhìn, thoát khỏi vòng lặp
                } else {
                    scrollBy(driver, 20);
                    Thread.sleep(50);
                }
            } catch (NoSuchElementException e) {
                // Cuộn xuống nếu không tìm thấy phần tử
                scrollBy(driver, 50);
                Thread.sleep(500); // Đợi một chút sau khi cuộn
            }
        }
        return incrementButton;
    }

    // Hàm cuộn từng bước
    private static void scrollBy(WebDriver driver, int pixels) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0," + pixels + ");");
    }

    // Hàm hoverAction
    private static void hoverAction(WebDriver driver, WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    // Hàm kiểm tra xem phần tử có nằm trong khung nhìn hay không
    private static boolean isElementInViewPort(WebDriver driver, WebElement element) {
        return (Boolean) ((JavascriptExecutor) driver).executeScript(
                "var rect = arguments[0].getBoundingClientRect();" +
                        "return (" +
                        "rect.top >= 0 && " +
                        "rect.left >= 0 && " +
                        "rect.bottom <= (window.innerHeight || document.documentElement.clientHeight) && " +
                        "rect.right <= (window.innerWidth || document.documentElement.clientWidth));",
                element);
    }
}