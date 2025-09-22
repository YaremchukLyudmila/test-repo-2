package edu.innotech.ui;

import edu.innotech.ui.pages.FlyPobedaPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class SeleniumTest {

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
    public void test() {
        FlyPobedaPage flyPobedaPage = new FlyPobedaPage(webDriver);
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        webDriver.get("https://pobeda.aero/");
        Assertions.assertEquals(flyPobedaPage.getTitle(), "Авиакомпания «Победа» - купить авиабилеты онлайн, дешёвые билеты на самолёт, прямые и трансферные рейсы с пересадками");
        Assertions.assertTrue(flyPobedaPage.existLogo());
        flyPobedaPage.moveMouseToInformation();
        Assertions.assertTrue(flyPobedaPage.проверкаВсплывающегоМеню());
    }
}
