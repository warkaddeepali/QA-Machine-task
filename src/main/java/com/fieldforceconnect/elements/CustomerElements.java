package com.fieldforceconnect.elements;

import org.openqa.selenium.By;

public class CustomerElements {
    public static final By MY_CUSTOMERS_MENU = By.xpath("//a[contains(@href, '/')]//span[text()='My Customers']");
    public static final By MY_CUSTOMER = By.xpath("//a[contains(@href, '/customers')]//span[text()='My Customer']");
    public static final By MANAGE = By.xpath("//button[@type='button' and @aria-haspopup='true' and contains(., 'Manage')]");
    public static final By MENU_NEW_CUSTOMER = By.xpath("//li[@role='menuitem' and @title='Create New Customer']");
    private static final String MODAL_CONTEXT = "//div[@role='dialog']";
    public static final By CUSTOMER_NAME = By.xpath(MODAL_CONTEXT + "//input[@name='LeadName']");
    public static final By REF_NO = By.xpath(MODAL_CONTEXT + "//input[@name='RefNo']");
    public static final By CONTACT_PERSON = By.xpath(MODAL_CONTEXT + "//input[@name='PersonName']");
    public static final By PHONE = By.xpath(MODAL_CONTEXT + "//input[@name='MobileNo']");
    public static final By TELEPHONE = By.xpath(MODAL_CONTEXT + "//input[@name='ContactNo']");
    public static final By EMAIL = By.xpath(MODAL_CONTEXT + "//input[@name='Email']");
    public static final By SAVE = By.xpath(MODAL_CONTEXT + "//button[@type='submit' or normalize-space()='Save']");
}
