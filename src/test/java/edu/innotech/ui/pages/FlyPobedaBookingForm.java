package edu.innotech.ui.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static edu.innotech.ui.Utils.DEFAULT_DURATION;

public class FlyPobedaBookingForm {

    private final SelenideElement ticketBookingSurname = $(byXpath("//input[@placeholder='Фамилия клиента']"));
    private final SelenideElement ticketBookingNumber = $(byXpath("//input[@placeholder='Номер бронирования или билета']"));
    private final SelenideElement ticketBookingFindButton = $(byXpath("//button/span[text()='Поиск']"));
    private final SelenideElement bookingTab = $(By.xpath("//button/span[text()='Управление бронированием']"));

    public void scrollAndSelectTicketBooking() {
        bookingTab.parent().shouldBe(visible, DEFAULT_DURATION);
        bookingTab.parent().click();
    }

    public void checkVisibilityBookingElementsVisible() {
        ticketBookingSurname.shouldBe(visible, DEFAULT_DURATION);
        ticketBookingNumber.shouldBe(visible, DEFAULT_DURATION);
        ticketBookingFindButton.shouldBe(visible, DEFAULT_DURATION);
    }

    public void setTicketBookingSurname(String text) {
        ticketBookingSurname.sendKeys(text);
    }

    public void setTicketBookingNumber(String text) {
        ticketBookingNumber.sendKeys(text);
    }

    public void clickTicketBookingFind() {
        ticketBookingFindButton.parent().submit();
    }
}
