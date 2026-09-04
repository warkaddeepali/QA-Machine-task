package com.fieldforceconnect.actions;

import com.fieldforceconnect.pages.LoginPage;
import org.openqa.selenium.WebDriver;

public class LoginActions {
    private final LoginPage loginPage;

    public LoginActions(WebDriver driver) {
        this.loginPage = new LoginPage(driver);
    }

    public void openApplication(String url) {
        loginPage.open(url);
    }

    public void login(String username, String password) {
        loginPage.login(username, password);
    }
}
