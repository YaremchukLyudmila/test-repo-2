package edu.innotech.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Utils {
    public static WebElement getParent(WebElement webElement) {
        return webElement.findElement(By.xpath("./.."));
    }

    public static void delay(int second) {
        try {
            Thread.sleep(second * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
