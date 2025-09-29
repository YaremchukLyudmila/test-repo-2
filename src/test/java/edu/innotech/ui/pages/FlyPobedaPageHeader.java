package edu.innotech.ui.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static edu.innotech.ui.Utils.DEFAULT_DURATION;

public class FlyPobedaPageHeader {

    private final SelenideElement logo = $(byXpath("//img[@alt='«Авиакомпания «Победа», Группа «Аэрофлот»']"));
    private final SelenideElement informationMenu = $(byTagAndText("a", "Информация"));
    private final SelenideElement flyPrepareTab = $(byTagAndText("a", "Подготовка к полёту"));
    private final SelenideElement infoTab = $(byTagAndText("a", "Полезная информация"));
    private final SelenideElement aboutTab = $(byTagAndText("a", "О компании"));

    public void checkLogoVisibility() {
        logo.shouldHave(visible, DEFAULT_DURATION);
    }

    public void moveMouseToInformation() {
        informationMenu.hover();
    }

    public void checkIsPreparingForFlightVisible() {
        flyPrepareTab.shouldBe(visible, DEFAULT_DURATION);
        infoTab.shouldBe(visible, DEFAULT_DURATION);
        aboutTab.shouldBe(visible, DEFAULT_DURATION);
    }
}
