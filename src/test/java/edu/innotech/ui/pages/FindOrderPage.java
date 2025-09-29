package edu.innotech.ui.pages;


import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class FindOrderPage {

    private final SelenideElement checkBox = $(byXpath("//div[@class='customCheckbox']"));
    private final SelenideElement findOrderButton = $(byTagAndText("button", "Найти заказ"));
    private final SelenideElement divOrderNotFound = $(byTagAndText("div", "Заказ с указанными параметрами не найден"));

    public void setCheckBoxAccept() {
        checkBox.shouldBe(visible, Duration.ofSeconds(30));
        checkBox.click();
    }


    public void clickFindOrderButton() {
        findOrderButton.click();
    }

    public SelenideElement getDivOrderNotFound() {
        return divOrderNotFound;
    }
}
