package edu.innotech.ui.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static edu.innotech.ui.Utils.delay;

public class FlyPobedaBookingForm {

    private static final By ticketBookingSurname = byXpath("//input[@placeholder='Фамилия клиента']");
    private static final By ticketBookingNumber = byXpath("//input[@placeholder='Номер бронирования или билета']");
    private static final By ticketBookingFindButton = byXpath("//button/span[text()='Поиск']");

    public void scrollAndSelectTicketBooking() {
        SelenideElement bookingTab = $(By.xpath("//button/span[text()='Управление бронированием']")).parent();
        bookingTab.click();
        delay(1);
    }

    public boolean isBookingElementsVisible() {
        return $(ticketBookingSurname).isDisplayed()
                && $(ticketBookingNumber).isDisplayed()
                && $(ticketBookingFindButton).isDisplayed();
    }

    public void setTicketBookingSurname(String text) {
        $(ticketBookingSurname).sendKeys(text);
    }

    public void setTicketBookingNumber(String text) {
        $(ticketBookingNumber).sendKeys(text);
    }

    public void clickTicketBookingFind() {
        $(ticketBookingFindButton).parent().submit();
    }
}
