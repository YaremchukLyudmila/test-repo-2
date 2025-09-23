package edu.innotech.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FindOrderPage {

    private final WebDriver webDriver;

    @FindBy(xpath = "//div[@class='customCheckbox']")
    private WebElement checkBoxAccept;

    @FindBy(xpath = "//button[text()='Найти заказ']")
    private WebElement findOrderButton;

    @FindBy(xpath = "//div[text()='Заказ с указанными параметрами не найден']")
    private WebElement divOrderNotFound;

    public FindOrderPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public void setCheckBoxAccept() {
        checkBoxAccept.click();
    }

    public void clickFindOrderButton() {
        findOrderButton.click();
    }

    public boolean isVisibleDivOrderNotFound() {
        return divOrderNotFound.isDisplayed();
    }
}
