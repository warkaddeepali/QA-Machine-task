package com.fieldforceconnect.tests;

import com.fieldforceconnect.actions.LoginActions;
import com.fieldforceconnect.actions.PunchInActions;
import com.fieldforceconnect.managers.ConfigManager;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PunchInTest extends BaseTest {

    @Test
    public void verifyPunchInToastMessage() {
        LoginActions loginActions = new LoginActions(driver);
        PunchInActions punchInActions = new PunchInActions(driver);

        loginActions.openApplication(ConfigManager.get("base.url"));
        loginActions.login(ConfigManager.get("test.username"), ConfigManager.get("test.password"));

        punchInActions.punchIn();
        String actualToast = punchInActions.getToastMessage();
        System.out.println("Captured Application Toast message: [" + actualToast + "]");
        Assert.assertNotNull(actualToast, "Toast container element returned null context references.");
        String normalizedToast = actualToast.toLowerCase();

        boolean isNotificationValid = normalizedToast.contains("timesheet")
                || normalizedToast.contains("updated")
                || normalizedToast.contains("successfully");

        Assert.assertTrue(isNotificationValid,
                "Expected a success confirmation alert message, but instead captured: [" + actualToast + "]");

    }
}
