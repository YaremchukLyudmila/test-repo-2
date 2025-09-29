package edu.innotech.ui.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static edu.innotech.ui.Utils.DEFAULT_DURATION;
import static edu.innotech.ui.Utils.delay;

public class FlyPobedaSearchForm {

    private final SelenideElement fieldFrom = $(byXpath("//input[@placeholder='Откуда']"));
    private final SelenideElement fieldTo = $(byXpath("//input[@placeholder='Куда']"));
    private final SelenideElement fieldDateFrom = $(byXpath("//input[@placeholder='Обратно']"));
    private final SelenideElement fieldDateTo = $(byXpath("//input[@placeholder='Туда']"));
    private final SelenideElement buttonFind = $(byXpath("//button/span[text()='Поиск']"));


    public void scrollToTicketFind() {
        $(byXpath("//button/span[text()='Поиск билета']")).scrollTo();
    }

    public void checkIsExistTicketFind() {
        fieldFrom.shouldBe(visible, DEFAULT_DURATION);
        fieldTo.shouldBe(visible, DEFAULT_DURATION);
        fieldDateFrom.shouldBe(visible, DEFAULT_DURATION);
        fieldDateTo.shouldBe(visible, DEFAULT_DURATION);
    }

    public void setTextFrom(String from) {
        fieldFrom.click();
        fieldFrom.sendKeys(Keys.BACK_SPACE);
        fieldFrom.sendKeys(from);
        delay(1);
        fieldFrom.press(Keys.DOWN);
        fieldFrom.pressEnter();
    }

    public void setTextTo(String from) {
        fieldTo.click();
        fieldTo.sendKeys(from);
        delay(1);
        fieldTo.press(Keys.DOWN);
        fieldTo.pressEnter();
    }


    public void submitFind() {
        buttonFind.submit();
    }


    public void checkIsErrorDateTo() {
        fieldDateTo.shouldBe(visible, DEFAULT_DURATION);
        fieldDateTo.parent().shouldHave(attribute("data-failed", "true"));
    }
}
