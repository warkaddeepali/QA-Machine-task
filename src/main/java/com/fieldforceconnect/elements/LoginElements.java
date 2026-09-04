package com.fieldforceconnect.elements;

import org.openqa.selenium.By;

public class LoginElements {
    public static final By USERNAME =
            By.xpath("//input[@name='username']");
    public static final By PASSWORD =
            By.xpath("//input[@name='password']");
    public static final By LOGIN_BUTTON =
            By.xpath("//button[@type='submit']//span[text()='Sign In']");
    public static final By LOGIN_WITH_OTP_BUTTON =
            By.xpath("//button[@type='button']//span[text()='Sign In With OTP']");
}


