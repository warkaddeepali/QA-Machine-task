package com.fieldforceconnect.pages;

import com.fieldforceconnect.elements.CustomerElements;
import com.fieldforceconnect.managers.WaitManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CustomerPage {
    private final WebDriver driver;

    public CustomerPage(WebDriver driver) {
        this.driver = driver;
    }
    public void openAddCustomer() {
        WaitManager.getWait(driver)
                .until(ExpectedConditions.elementToBeClickable(CustomerElements.MY_CUSTOMERS_MENU))
                .click();
        WaitManager.getWait(driver)
                .until(ExpectedConditions.elementToBeClickable(CustomerElements.MY_CUSTOMER))
                .click();
        WebElement manageButton = WaitManager.getWait(driver)
                .until(ExpectedConditions.presenceOfElementLocated(CustomerElements.MANAGE));

        try {
            manageButton.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", manageButton);
        }
        WaitManager.getWait(driver)
                .until(ExpectedConditions.elementToBeClickable(CustomerElements.MENU_NEW_CUSTOMER))
                .click();
    }

    public void fillNewCustomerForm(String leadName, String refNo, String contactPerson,
                                    String mobile, String telephone, String email) {
        WaitManager.getWait(driver)
                .until(ExpectedConditions.visibilityOfElementLocated(CustomerElements.CUSTOMER_NAME))
                .clear();
        driver.findElement(CustomerElements.CUSTOMER_NAME).sendKeys(leadName);

        if (refNo != null) {
            driver.findElement(CustomerElements.REF_NO).clear();
            driver.findElement(CustomerElements.REF_NO).sendKeys(refNo);
        }

        if (contactPerson != null) {
            driver.findElement(CustomerElements.CONTACT_PERSON).clear();
            driver.findElement(CustomerElements.CONTACT_PERSON).sendKeys(contactPerson);
        }

        WaitManager.getWait(driver)
                .until(ExpectedConditions.visibilityOfElementLocated(CustomerElements.PHONE))
                .clear();
        driver.findElement(CustomerElements.PHONE).sendKeys(mobile);

        if (telephone != null) {
            driver.findElement(CustomerElements.TELEPHONE).clear();
            driver.findElement(CustomerElements.TELEPHONE).sendKeys(telephone);
        }

        WaitManager.getWait(driver)
                .until(ExpectedConditions.visibilityOfElementLocated(CustomerElements.EMAIL))
                .clear();
        driver.findElement(CustomerElements.EMAIL).sendKeys(email);
    }

    public void clickSave() {
        WaitManager.getWait(driver)
                .until(ExpectedConditions.elementToBeClickable(CustomerElements.SAVE))
                .click();
    }

    public boolean isCustomerAdded(String customerName) {
        String escapeXpath = "//*[normalize-space()=concat('', \"" + customerName + "\", '')]";
        By customer = By.xpath(escapeXpath);
        try {
            return WaitManager.getWait(driver)
                    .until(ExpectedConditions.visibilityOfElementLocated(customer))
                    .isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
