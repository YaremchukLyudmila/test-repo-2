package edu.innotech.ui;

import com.codeborne.selenide.WebDriverRunner;
import edu.innotech.ui.pages.*;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selenide.*;
import static edu.innotech.ui.Utils.delay;

public class SeleniumTest {

    @AfterEach
    public void closeWindowSelenide() {
        closeWindow();
    }

    @AfterAll
    public static void close() {
        closeWebDriver();
    }

    @Test
    public void test1() {
        open("https://pobeda.aero/");
        WebDriverRunner.getWebDriver().manage().window().maximize();
        $("title").shouldHave(attribute(
                "text",
                "Авиакомпания «Победа» - купить авиабилеты онлайн, дешёвые билеты на самолёт, прямые и трансферные рейсы с пересадками"));
        FlyPobedaPageHeader flyPobedaPage = new FlyPobedaPageHeader();
        Assertions.assertTrue(flyPobedaPage.getLogo().isDisplayed());
        flyPobedaPage.moveMouseToInformation();
        Assertions.assertTrue(flyPobedaPage.isPreparingForFlightVisible());
    }

    @Test
    public void test2() {
        open("https://pobeda.aero/");
        WebDriverRunner.getWebDriver().manage().window().maximize();
        $("title").shouldHave(attribute(
                "text",
                "Авиакомпания «Победа» - купить авиабилеты онлайн, дешёвые билеты на самолёт, прямые и трансферные рейсы с пересадками"));
        FlyPobedaPageHeader flyPobedaPage = new FlyPobedaPageHeader();
        Assertions.assertTrue(flyPobedaPage.getLogo().isDisplayed());

        FlyPobedaSearchForm flyPobedaSearchForm = new FlyPobedaSearchForm();
        flyPobedaSearchForm.scrollToTicketFind();
        Assertions.assertTrue(flyPobedaSearchForm.isExistTicketFind());
        flyPobedaSearchForm.setTextFrom("Москва");
        flyPobedaSearchForm.setTextTo("Санкт-Петербург");
        flyPobedaSearchForm.submitFind();
        Assertions.assertTrue(flyPobedaSearchForm.isErrorDateTo());
        delay(5);
    }

    @Test
    public void test3() {
        open("https://pobeda.aero/");
        WebDriverRunner.getWebDriver().manage().window().maximize();
        $("title").shouldHave(attribute(
                "text",
                "Авиакомпания «Победа» - купить авиабилеты онлайн, дешёвые билеты на самолёт, прямые и трансферные рейсы с пересадками"));
        FlyPobedaPageHeader flyPobedaPage = new FlyPobedaPageHeader();
        Assertions.assertTrue(flyPobedaPage.getLogo().isDisplayed());

        delay(1);
        FlyPobedaBookingForm bookingForm = new FlyPobedaBookingForm();
        bookingForm.scrollAndSelectTicketBooking();

        Assertions.assertTrue(bookingForm.isBookingElementsVisible());
        bookingForm.setTicketBookingSurname("Qwerty");
        bookingForm.setTicketBookingNumber("XXXXXX");
        bookingForm.clickTicketBookingFind();
        switchTo().window(1);
        delay(3);

        FindOrderPage findOrderPage = new FindOrderPage();
        findOrderPage.setCheckBoxAccept();
        findOrderPage.clickFindOrderButton();
        delay(5); // на случай проверки на бота
        Assertions.assertTrue(findOrderPage.isVisibleDivOrderNotFound());
    }
}
