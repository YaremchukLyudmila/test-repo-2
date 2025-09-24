package edu.innotech.ui.pages;


import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class FindOrderPage {

    public void setCheckBoxAccept() {
        $(byXpath("//div[@class='customCheckbox']")).click();
    }


    public void clickFindOrderButton() {
        $(byTagAndText("button", "Найти заказ")).click();
    }

    public boolean isVisibleDivOrderNotFound() {
        return $(byTagAndText("div", "Заказ с указанными параметрами не найден")).isDisplayed();
    }
}
