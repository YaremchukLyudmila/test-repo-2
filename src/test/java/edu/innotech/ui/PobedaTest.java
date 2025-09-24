package edu.innotech.ui;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Map;

import static edu.innotech.ui.Utils.delay;
import static edu.innotech.ui.Utils.getParent;

public class PobedaTest {

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
    public void open() {
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(90));
        webDriver.get("https://google.com/");
        webDriver.manage().window().maximize();
        WebElement findTextArea = webDriver.findElement(By.xpath("//textarea[@title='Поиск']"));
        findTextArea.sendKeys("Сайт компании Победа");
        findTextArea.sendKeys(Keys.ENTER);

        // Добавлено ожидание на случай проверки на бота
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofMinutes(3));
        By targetLinkElementLocator = By.xpath("//span[text()='Победа']");
        WebElement targetLink = wait.until(ExpectedConditions.visibilityOfElementLocated(targetLinkElementLocator));
        targetLink.click();

        wait = new WebDriverWait(webDriver, Duration.ofMinutes(3));
        By kalinigradElementLocator = By.xpath("//div[contains(text(), 'в Калининград')]");
        System.out.println("Начинаем ожидание");
        WebElement kalinigradElement = wait.until(ExpectedConditions.visibilityOfElementLocated(kalinigradElementLocator));
        System.out.println("Дождались: " + kalinigradElement.getText());
        Assertions.assertEquals(kalinigradElement.getText(), "Полетели в Калининград!");

        WebElement changeLanguageButton = webDriver.findElement(By.xpath("//button[text()='РУС']"));
        changeLanguageButton.click();
        final WebElement enLanguageButton = webDriver.findElement(By.xpath("//div[text()='English']"));
        enLanguageButton.click();
        delay(3);

        Map<String, WebElement> enElements = Map.of(
                "Ticket search",
                webDriver.findElement(By.xpath("//div/div/div/button[1]/span[contains(@class, 'root-inner')]")),
                "Online check-in",
                        webDriver.findElement(By.xpath("//div/div/div/button[2]/span[contains(@class, 'root-inner')]")),
                "Manage my booking",
                webDriver.findElement(By.xpath("//div/div/div/button[3]/span[contains(@class, 'root-inner')]")));
        // Применение textToBePresentInElement здесь выглядит не логично, так как текст уже и так часть локатора,
        // но иначе нормально локатор написать не знаю как - нет постоянных id/классов у элементов
        // А решение с textToBePresentInElement() или textToBePresentInElementLocated() не сработало,
        // вероятно потому смена языка перезагружает страницу
        enElements.forEach((text, enElement) -> {
            Assertions.assertEquals(getParent(enElement).getText(), text);
        });
    }
}



