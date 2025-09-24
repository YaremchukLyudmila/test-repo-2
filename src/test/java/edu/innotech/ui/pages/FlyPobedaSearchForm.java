package edu.innotech.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static edu.innotech.ui.Utils.delay;

public class FlyPobedaSearchForm {

    private static final By fieldFrom = byXpath("//input[@placeholder='Откуда']");
    private static final By fieldTo = byXpath("//input[@placeholder='Куда']");
    private static final By fieldDateFrom = byXpath("//input[@placeholder='Обратно']");
    private static final By fieldDateTo = byXpath("//input[@placeholder='Туда']");


    public void scrollToTicketFind() {
        $(byXpath("//button/span[text()='Поиск билета']")).scrollTo();
    }

    public boolean isExistTicketFind() {
        return $(fieldFrom).isDisplayed()
                && $(fieldTo).isDisplayed()
                && $(fieldDateFrom).isDisplayed()
                && $(fieldDateTo).isDisplayed();
    }

    public void setTextFrom(String from) {
        $(fieldFrom).click();
        delay(1);
        $(fieldFrom).sendKeys(Keys.BACK_SPACE);
        $(fieldFrom).sendKeys(from);
        delay(1);
        $(fieldFrom).press(Keys.DOWN);
        $(fieldFrom).pressEnter();
    }

    public void setTextTo(String from) {
        $(fieldTo).click();
        delay(1);
        $(fieldTo).sendKeys(from);
        delay(1);
        $(fieldTo).press(Keys.DOWN);
        $(fieldTo).pressEnter();
    }


    public void submitFind() {
        $(byXpath("//button/span[text()='Поиск']")).submit();
    }


    public boolean isErrorDateTo() {
        return "true".equals($(fieldDateTo).parent().getAttribute("data-failed"));
    }
}
