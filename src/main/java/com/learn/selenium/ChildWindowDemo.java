package com.learn.selenium;

import com.learn.selenium.utils.LinkWebPratice;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.SpringApplication;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

public class ChildWindowDemo {
    public static void main(String[] args) throws InterruptedException {

        System.setProperty("java.awt.headless", "false");
        SpringApplication.run(ChildWindowDemo.class, args);

        // Kiểm tra trạng thái headless
        System.out.println("Headless mode: " + System.getProperty("java.awt.headless", "false"));

        // Tắt chế độ headless cho Firefox
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--width=1280", "--height=720");
        WebDriver driver = new FirefoxDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();

        // Kiểm tra Firefox chạy với GUI
        System.out.println("Firefox headless: " + options.getCapability("moz:headless"));

        // Mở trang parent
        driver.get(LinkWebPratice.LOGIN.getUrlLink());
        Thread.sleep(2000);

        // Nhấp vào liên kết để mở child window
        WebElement link = driver.findElement(By.cssSelector(".blinkingText"));
        // Focus vào cửa sổ trình duyệt trước khi thực hiện thao tác chuột
        focusBrowserWindow(driver);
        Thread.sleep(1000);

        // Di chuyển con trỏ chuột và nhấp vào liên kết bằng Robot
        link.click();
        Thread.sleep(2000);

        // Lấy tất cả Window Handle IDs
        Set<String> windows = driver.getWindowHandles();
        Iterator<String> it = windows.iterator();

        // Lấy ID của parent và child window
        String parentID = it.next();
        String childID = it.next();

        // Chuyển sang child window
        driver.switchTo().window(childID);
        Thread.sleep(1000);

        // Lấy văn bản từ child window
        WebElement emailText = findClickableElement(driver, By.cssSelector(".im-para.red"));
        String fullText = emailText.getText();
        System.out.println("Văn bản từ child window: " + fullText);

        // Focus vào cửa sổ trình duyệt trước khi bôi đen
        focusBrowserWindow(driver);
        Thread.sleep(1000);

        // Thao tác bôi đen và copy chỉ đoạn email bằng phím
        highlightAndCopyEmail(driver, emailText, fullText);

        // Chuyển về parent window bằng cách nhấp vào tab parent
        switchToParentTab(driver, parentID);

        // Focus vào cửa sổ trình duyệt trước khi paste
        focusBrowserWindow(driver);
        Thread.sleep(1000);

        // Nhập email vào ô username bằng cách paste
        WebElement usernameField = driver.findElement(By.id("username"));
        pasteText(driver, usernameField);

        // Dừng để quan sát kết quả cuối cùng
        Thread.sleep(3000);

        // Đóng trình duyệt
        driver.quit();
    }

    private static void focusBrowserWindow(WebDriver driver) throws InterruptedException {
        try {
            Robot robot = new Robot();
            org.openqa.selenium.Point windowPosition = driver.manage().window().getPosition();
            int windowX = windowPosition.getX();
            int windowY = windowPosition.getY();

            // Nhấp vào cửa sổ trình duyệt để focus
            robot.mouseMove(windowX + 50, windowY + 50);
            Thread.sleep(500);
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    private static WebElement findClickableElement(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    private static void highlightAndCopyEmail(WebDriver driver, WebElement element, String text) throws InterruptedException {
        try {
            Robot robot = new Robot();
            JavascriptExecutor js = (JavascriptExecutor) driver;

            // Cuộn trang để phần tử nằm giữa khung nhìn
            js.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'center'});", element);
            Thread.sleep(1000);

            // Focus vào phần tử văn bản
            js.executeScript("arguments[0].focus();", element); // Sử dụng Javascript để focus
            Thread.sleep(500);

            // Trích xuất email từ văn bản
            String email = extractEmail(text);
            int emailStartIndex = text.indexOf(email); // Vị trí bắt đầu của email trong văn bản
            int emailLength = email.length();

            // Di chuyển con trỏ đến ký tự đầu tiên của email
            robot.keyPress(KeyEvent.VK_HOME); // Đưa con trỏ về đầu văn bản
            Thread.sleep(200);
            robot.keyRelease(KeyEvent.VK_HOME);
            Thread.sleep(200);

            // Di chuyển con trỏ đến đầu email (số ký tự từ đầu đến đầu email)
            for (int i = 0; i < emailStartIndex; i++) {
                robot.keyPress(KeyEvent.VK_RIGHT);
                Thread.sleep(100);
                robot.keyRelease(KeyEvent.VK_RIGHT);
            }
            Thread.sleep(500);

            // Bôi đen từng ký tự của email bằng Shift + Arrow Right
            robot.keyPress(KeyEvent.VK_SHIFT);
            Thread.sleep(200);

            for (int i = 0; i < emailLength; i++) {
                robot.keyPress(KeyEvent.VK_RIGHT);
                Thread.sleep(300); // Chờ 300ms để thấy rõ từng ký tự được bôi đen
                robot.keyRelease(KeyEvent.VK_RIGHT);
                Thread.sleep(100); // Chờ thêm để đảm bảo bôi đen chính xác
            }

            robot.keyRelease(KeyEvent.VK_SHIFT);
            Thread.sleep(500);

            // Nhấn Ctrl + C để copy
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_C);
            Thread.sleep(500);
            robot.keyRelease(KeyEvent.VK_C);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            System.out.println("Đã bôi đen và copy email");
            Thread.sleep(1000);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    private static String extractEmail(String text) {
        // Trích xuất email từ văn bản (giả định email là chuỗi trước " with")
        int atIndex = text.indexOf(" at ");
        if (atIndex != -1) {
            int spaceIndex = text.indexOf(" with", atIndex);
            if (spaceIndex != -1) {
                return text.substring(atIndex + 4, spaceIndex).trim(); // Lấy "mentor@rahulshettyacademy.com"
            }
        }
        return ""; // Trả về rỗng nếu không tìm thấy email
    }

    private static void switchToParentTab(WebDriver driver, String parentID) throws InterruptedException {
        try {
            Robot robot = new Robot();

            // Giả định vị trí tab parent
            org.openqa.selenium.Point windowPosition = driver.manage().window().getPosition();
            int windowX = windowPosition.getX();
            int windowY = windowPosition.getY();
            int tabX = windowX + 50;
            int tabY = windowY + 20;

            // Di chuyển chuột đến vị trí tab parent và nhấp
            robot.mouseMove(tabX, tabY);
            Thread.sleep(1000);
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            Thread.sleep(1000);

            // Chuyển focus bằng Selenium
            driver.switchTo().window(parentID);
            System.out.println("Đã chuyển về tab parent");
            Thread.sleep(1000);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    private static void pasteText(WebDriver driver, WebElement element) throws InterruptedException {
        try {
            Robot robot = new Robot();

            // Lấy tọa độ cửa sổ trình duyệt
            org.openqa.selenium.Point windowPosition = driver.manage().window().getPosition();
            int windowX = windowPosition.getX();
            int windowY = windowPosition.getY();

            // Lấy tọa độ và kích thước của phần tử
            org.openqa.selenium.Point elementLocation = element.getLocation();
            int elementX = elementLocation.getX();
            int elementY = elementLocation.getY();
            int width = element.getSize().getWidth();
            int height = element.getSize().getHeight();

            // Tính toán tọa độ trung tâm của ô username
            int pasteX = windowX + elementX + (width / 2);
            int pasteY = windowY + elementY + (height / 2);

            // Di chuyển chuột đến ô username và nhấp
            robot.mouseMove(pasteX, pasteY);
            Thread.sleep(1000);
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            Thread.sleep(500);

            // Nhấn Ctrl + V để paste
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            Thread.sleep(500);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            System.out.println("Đã paste văn bản vào ô username");
            Thread.sleep(1000);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
}