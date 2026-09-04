package com.fieldforceconnect.tests;

import com.fieldforceconnect.actions.LoginActions;
import com.fieldforceconnect.managers.ConfigManager;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return new Object[][] {
                {ConfigManager.get("test.username"), ConfigManager.get("test.password")}
        };
    }

    @Test(dataProvider = "loginData")
    public void verifyLogin(String username, String password) {
        LoginActions loginActions = new LoginActions(driver);
        loginActions.openApplication(ConfigManager.get("base.url"));
        loginActions.login(username, password);

    }

}
