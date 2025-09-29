package edu.innotech.ui;

import com.codeborne.selenide.WebDriverRunner;
import edu.innotech.ui.pages.FindOrderPage;
import edu.innotech.ui.pages.FlyPobedaBookingForm;
import edu.innotech.ui.pages.FlyPobedaPageHeader;
import edu.innotech.ui.pages.FlyPobedaSearchForm;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.closeWindow;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.switchTo;

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
        flyPobedaPage.checkLogoVisibility();
        flyPobedaPage.moveMouseToInformation();

        flyPobedaPage.checkIsPreparingForFlightVisible();
    }

    @Test
    public void test2() {
        open("https://pobeda.aero/");
        WebDriverRunner.getWebDriver().manage().window().maximize();
        $("title").shouldHave(attribute(
                "text",
                "Авиакомпания «Победа» - купить авиабилеты онлайн, дешёвые билеты на самолёт, прямые и трансферные рейсы с пересадками"));
        FlyPobedaPageHeader flyPobedaPage = new FlyPobedaPageHeader();
        flyPobedaPage.checkLogoVisibility();

        FlyPobedaSearchForm flyPobedaSearchForm = new FlyPobedaSearchForm();
        flyPobedaSearchForm.scrollToTicketFind();
        flyPobedaSearchForm.checkIsExistTicketFind();
        flyPobedaSearchForm.setTextFrom("Москва");
        flyPobedaSearchForm.setTextTo("Санкт-Петербург");
        flyPobedaSearchForm.submitFind();
        flyPobedaSearchForm.checkIsErrorDateTo();
    }

    @Test
    public void test3() {
        open("https://pobeda.aero/");
        WebDriverRunner.getWebDriver().manage().window().maximize();
        $("title").shouldHave(attribute(
                "text",
                "Авиакомпания «Победа» - купить авиабилеты онлайн, дешёвые билеты на самолёт, прямые и трансферные рейсы с пересадками"));
        FlyPobedaPageHeader flyPobedaPage = new FlyPobedaPageHeader();
        flyPobedaPage.checkLogoVisibility();

        FlyPobedaBookingForm bookingForm = new FlyPobedaBookingForm();
        bookingForm.scrollAndSelectTicketBooking();

        bookingForm.checkVisibilityBookingElementsVisible();
        bookingForm.setTicketBookingSurname("Qwerty");
        bookingForm.setTicketBookingNumber("XXXXXX");
        bookingForm.clickTicketBookingFind();
        switchTo().window(1);

        FindOrderPage findOrderPage = new FindOrderPage();
        findOrderPage.setCheckBoxAccept();
        findOrderPage.clickFindOrderButton();
        findOrderPage.getDivOrderNotFound().shouldBe(visible, Duration.ofMinutes(1));
    }
}
