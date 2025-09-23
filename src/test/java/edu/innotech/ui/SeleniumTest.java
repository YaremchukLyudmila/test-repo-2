package edu.innotech.ui;

import edu.innotech.ui.pages.FindOrderPage;
import edu.innotech.ui.pages.FlyPobedaPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static edu.innotech.ui.Utils.delay;

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
    @Disabled
    public void test1() {
        FlyPobedaPage flyPobedaPage = new FlyPobedaPage(webDriver);
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        webDriver.get("https://pobeda.aero/");
        Assertions.assertEquals(flyPobedaPage.getTitle(), "Авиакомпания «Победа» - купить авиабилеты онлайн, дешёвые билеты на самолёт, прямые и трансферные рейсы с пересадками");
        Assertions.assertTrue(flyPobedaPage.existLogo());
        flyPobedaPage.moveMouseToInformation();
        Assertions.assertTrue(flyPobedaPage.проверкаВсплывающегоМеню());
    }

    @Test
    @Disabled
    public void test2() {
        FlyPobedaPage flyPobedaPage = new FlyPobedaPage(webDriver);
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        webDriver.manage().window().maximize();
        webDriver.get("https://pobeda.aero/");
        Assertions.assertEquals(flyPobedaPage.getTitle(), "Авиакомпания «Победа» - купить авиабилеты онлайн, дешёвые билеты на самолёт, прямые и трансферные рейсы с пересадками");
        Assertions.assertTrue(flyPobedaPage.existLogo());

        flyPobedaPage.scrollToTicketFind();
        Assertions.assertTrue(flyPobedaPage.isExistTicketFind());
        flyPobedaPage.setTextFrom("Москва");
        flyPobedaPage.setTextTo("Санкт-Петербург");
        flyPobedaPage.submitFind();
        Assertions.assertTrue(flyPobedaPage.isErrorDateTo());
    }

    @Test
    public void test3() {
        FlyPobedaPage flyPobedaPage = new FlyPobedaPage(webDriver);
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        webDriver.manage().window().maximize();
        webDriver.get("https://pobeda.aero/");
        Assertions.assertEquals(flyPobedaPage.getTitle(), "Авиакомпания «Победа» - купить авиабилеты онлайн, дешёвые билеты на самолёт, прямые и трансферные рейсы с пересадками");
        Assertions.assertTrue(flyPobedaPage.existLogo());

        flyPobedaPage.selectTicketBooking();
        Assertions.assertTrue(flyPobedaPage.isBookingElementsVisible());
        flyPobedaPage.setTicketBookingSurname("Qwerty");
        flyPobedaPage.setTicketBookingNumber("XXXXXX");
        flyPobedaPage.clickTicketBookingFind();
        switchToNewTab();

        delay(10);
        FindOrderPage findOrderPage = new FindOrderPage(webDriver);
        findOrderPage.setCheckBoxAccept();
        findOrderPage.clickFindOrderButton();
        Assertions.assertTrue(findOrderPage.isVisibleDivOrderNotFound());
    }

    private static void switchToNewTab() {
        for (String window: webDriver.getWindowHandles()) {
            if (!window.equals(webDriver.getWindowHandle())) {
                webDriver = webDriver.switchTo().window(window);
                return;
            }
        }
    }
}
