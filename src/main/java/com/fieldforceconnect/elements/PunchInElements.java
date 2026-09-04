package com.fieldforceconnect.elements;

import org.openqa.selenium.By;

public class PunchInElements {
    public static final By PUNCH_IN_MENU = By.xpath("//a[contains(@href, '/attendance')]//span[text()='Attendance']");
    public static final By ADD_NEW_BUTTON = By.xpath("//button[contains(., 'Add New')]");
    private static final String MODAL_CONTEXT = "//*[@role='dialog']";
    public static final By PUNCH_IN_DATE_ICON = By.xpath(MODAL_CONTEXT + "//label[contains(text(),'Punch In Date')]/following-sibling::div//button");
    public static final By PUNCH_IN_TIME_ICON = By.xpath(MODAL_CONTEXT + "//label[contains(text(),'Punch In Time')]/following-sibling::div//button");
    public static final By PUNCH_OUT_DATE_ICON = By.xpath(MODAL_CONTEXT + "//label[contains(text(),'Punch Out Date')]/following-sibling::div//button");
    public static final By PUNCH_OUT_TIME_ICON = By.xpath(MODAL_CONTEXT + "//label[contains(text(),'Punch Out Time')]/following-sibling::div//button");
    public static final By CURRENT_DAY_SELECTION = By.xpath("//button[contains(@class, 'MuiPickersDay-today')]");
    public static final By DIALOG_TITLE_HEADER = By.xpath(MODAL_CONTEXT + "//h2");
    public static final By POPOVER_BACKDROP = By.xpath("//div[contains(@class, 'MuiBackdrop-root') or contains(@class, 'MuiPopover-root')]");
    public static final By SAVE_BUTTON = By.xpath(MODAL_CONTEXT + "//button[@type='submit' or normalize-space()='Save' or normalize-space()='Submit']");
    public static final By GLOBAL_TOAST_MESSAGE = By.xpath("//div[@data-rht-toaster]//div[contains(@class, 'Toast') or @role='alert' or text()]");
}
