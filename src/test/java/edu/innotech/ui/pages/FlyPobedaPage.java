package edu.innotech.ui.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static edu.innotech.ui.Utils.delay;
import static edu.innotech.ui.Utils.getParent;

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

    @FindBy(xpath = "//button/div[text()='Поиск билета']")
    private WebElement ticketFind;

    @FindBy(xpath = "//button/div[text()='Управление бронированием']")
    private WebElement ticketBooking;

    @FindBy(xpath = "//input[@placeholder='Откуда']")
    private WebElement fieldFrom;

    @FindBy(xpath = "//input[@placeholder='Куда']")
    private WebElement fieldTo;

    @FindBy(xpath = "//input[@placeholder='Обратно']")
    private WebElement fieldDateFrom;

    @FindBy(xpath = "//input[@placeholder='Туда']")
    private WebElement fieldDateTo;

    @FindBy(xpath = "//button/span[text()='Поиск']")
    private WebElement buttonFind;


    @FindBy(xpath = "//input[@placeholder='Фамилия клиента']")
    private WebElement ticketBookingSurname;

    @FindBy(xpath = "//input[@placeholder='Номер бронирования или билета']")
    private WebElement ticketBookingNumber;

    @FindBy(xpath = "//button/span[text()='Поиск']")
    private WebElement ticketBookingFindButton;

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

    public void scrollToTicketFind() {
        Actions actions = new Actions(webDriver);
        actions.scrollToElement(getParent(ticketFind)).perform();
    }

    public boolean isExistTicketFind() {
        return fieldFrom.isDisplayed() && fieldDateFrom.isDisplayed() && fieldTo.isDisplayed() && fieldDateTo.isDisplayed();
    }

    public void setTextFrom(String from) {
        fieldFrom.click();
        delay(1);
        fieldFrom.sendKeys(Keys.BACK_SPACE);
        fieldFrom.sendKeys(from);
        delay(1);
        fieldFrom.sendKeys(Keys.DOWN);
        fieldFrom.sendKeys(Keys.ENTER);
    }

    public void setTextTo(String to) {
        fieldTo.click();
        fieldTo.sendKeys(to);
        delay(1);
        fieldTo.sendKeys(Keys.DOWN);
        fieldTo.sendKeys(Keys.ENTER);
    }

    public void submitFind() {
        buttonFind.submit();
    }

    public boolean isErrorDateTo() {
        return "true".equals(getParent(fieldDateTo).getAttribute("data-failed"));
    }

    public void selectTicketBooking() {
        ticketBooking.click();
        delay(1);
    }

    public boolean isBookingElementsVisible() {
        return ticketBookingSurname.isDisplayed() && ticketBookingNumber.isDisplayed() && ticketBookingFindButton.isDisplayed();
    }

    public void setTicketBookingSurname(String text) {
        ticketBookingSurname.sendKeys(text);
    }

    public void setTicketBookingNumber(String text) {
        ticketBookingNumber.sendKeys(text);
    }

    public void clickTicketBookingFind() {
        ticketBookingFindButton.click();
    }
}
