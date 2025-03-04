package com.learn.selenium.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.awt.*;

public class MousePointerUI {

    public static void moveMouseToElement(WebDriver driver, WebElement element) {
        try {
            Robot robot = new Robot();

            // Lấy tọa độ của cửa sổ trình duyệt
            org.openqa.selenium.Point windowPosition = driver.manage().window().getPosition();
            int windowX = windowPosition.getX();
            int windowY = windowPosition.getY();

            // Lấy tọa độ của phần tử trong khung nhìn hiện tại bằng Javascript
            JavascriptExecutor js = (JavascriptExecutor) driver;
            String script = "return arguments[0].getBoundingClientRect();";
            Object rect = js.executeScript(script, element);

            // Parse tọa độ từ đối tượng DOMRect
            double elementX = ((Number) ((java.util.Map<?, ?>) rect).get("left")).doubleValue();
            double elementY = ((Number) ((java.util.Map<?, ?>) rect).get("top")).doubleValue();
            double width = ((Number) ((java.util.Map<?, ?>) rect).get("width")).doubleValue();
            double height = ((Number) ((java.util.Map<?, ?>) rect).get("height")).doubleValue();

            // Tính toán tọa độ tuyệt đối trên màn hình, nhắm vào trung tâm phần tử
            int absoluteX = windowX + (int) elementX + (int) (width / 2);
            int absoluteY = windowY + (int) elementY + (int) (height / 2);

            // Di chuyển chuột đến tọa độ tuyệt đối
            robot.mouseMove(absoluteX, absoluteY);
            System.out.println("Đã di chuyển chuột đến: (" + absoluteX + ", " + absoluteY + ")");

            Thread.sleep(500); // Dừng
        } catch (AWTException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
