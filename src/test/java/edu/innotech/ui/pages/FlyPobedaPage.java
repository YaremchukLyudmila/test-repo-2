package edu.innotech.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FlyPobedaPage {

    private final WebDriver webDriver;

    @FindBy(xpath = "//img[@alt='«Авиакомпания «Победа», Группа «Аэрофлот»']")
    private WebElement logo;

    @FindBy(xpath = "//a[text()='Информация']")
    private WebElement information;

    @FindBy(xpath = "//a[text()='Подготовка к полёту']")
    private WebElement подготовкаКПолету;

    @FindBy(xpath = "//a[text()='Полезная информация']")
    private WebElement полезнаяИнформация;

    @FindBy(xpath = "//a[text()='О компании']")
    private WebElement оКомпани;

    public FlyPobedaPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public String getTitle() {
        return webDriver.getTitle();
    }

    public boolean existLogo() {
        return logo.isDisplayed();
    }

    public void moveMouseToInformation() {
        Actions actions = new Actions(webDriver);
        actions.moveToElement(information).perform();
    }

    public boolean проверкаВсплывающегоМеню() {
        return подготовкаКПолету.isDisplayed() && полезнаяИнформация.isDisplayed() && оКомпани.isDisplayed();
    }
}
