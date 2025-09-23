package edu.innotech;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class PikabuTest {

    static WebDriver webDriver;

    @BeforeAll
    public static void init() {
        webDriver = new ChromeDriver();
    }

    @AfterAll
    public static void close() {
        webDriver.close();
    }

    @Test
    @Order(1)
    public void open() {
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        webDriver.get("https://pikabu.ru/");
        Assertions.assertEquals(webDriver.getTitle(), "Горячее – самые интересные и обсуждаемые посты | Пикабу");
    }

    @Test
    @Order(2)
    public void enter() {
        WebElement enterButton = webDriver.findElement(By.xpath("//div[@class='header-right-menu']/button"));
        enterButton.click();
        WebElement username = webDriver.findElement(By.xpath("//input[@name='username']"));
        WebElement password = webDriver.findElement(By.xpath("//input[@name='password']"));
        WebElement enterButton2 = webDriver.findElement(By.xpath("//button[@type='submit']"));

        username.sendKeys("Qwerty");
        password.sendKeys("Qwerty");
        enterButton2.click();

        WebElement errorMessage = webDriver.findElement(By.xpath("//span[text()='Ошибка. Вы ввели неверные данные авторизации']"));
        Assertions.assertTrue(errorMessage.isDisplayed());
    }

}
