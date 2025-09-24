package edu.innotech.ui.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class FlyPobedaPageHeader {
    public SelenideElement getLogo() {
        return $(byXpath("//img[@alt='«Авиакомпания «Победа», Группа «Аэрофлот»']"));
    }

    public void moveMouseToInformation() {
        $(byTagAndText("a", "Информация")).hover();
    }

    public boolean isPreparingForFlightVisible() {
        return $(byTagAndText("a", "Подготовка к полёту")).isDisplayed()
                && $(byTagAndText("a", "Полезная информация")).isDisplayed()
                && $(byTagAndText("a", "О компании")).isDisplayed();
    }
}
