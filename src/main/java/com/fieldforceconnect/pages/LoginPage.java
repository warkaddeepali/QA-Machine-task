package com.fieldforceconnect.pages;

import com.fieldforceconnect.elements.LoginElements;
import com.fieldforceconnect.managers.WaitManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage {
    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open(String url) {
        driver.get(url);
    }

    public void enterUsername(String username) {
        WaitManager.getWait(driver)
                .until(ExpectedConditions.visibilityOfElementLocated(LoginElements.USERNAME))
                .clear();
        driver.findElement(LoginElements.USERNAME).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(LoginElements.PASSWORD).clear();
        driver.findElement(LoginElements.PASSWORD).sendKeys(password);
    }

    public void clickLogin() {
        WaitManager.getWait(driver)
                .until(ExpectedConditions.elementToBeClickable(LoginElements.LOGIN_BUTTON))
                .click();
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }
}
