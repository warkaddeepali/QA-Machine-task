package com.fieldforceconnect.actions;

import com.fieldforceconnect.pages.PunchInPage;
import org.openqa.selenium.WebDriver;

public class PunchInActions {
    private final PunchInPage punchInPage;

    public PunchInActions(WebDriver driver) {
        this.punchInPage = new PunchInPage(driver);
    }

    public void punchIn() {
        punchInPage.navigateToPunchInModule();
        punchInPage.clickAddNew();
        punchInPage.enterFullTimesheetSequence();
        punchInPage.clickSave();
    }
    public String getToastMessage() {
        return punchInPage.getToastMessageText();
    }
}
