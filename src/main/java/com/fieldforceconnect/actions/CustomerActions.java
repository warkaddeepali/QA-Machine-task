package com.fieldforceconnect.actions;

import com.fieldforceconnect.pages.CustomerPage;
import org.openqa.selenium.WebDriver;

public class CustomerActions {
    private final CustomerPage customerPage;

    public CustomerActions(WebDriver driver) {
        this.customerPage = new CustomerPage(driver);
    }
    public void openAddCustomer() {
        customerPage.openAddCustomer();
    }
    public void addCustomer(String name, String refNo, String contactPerson,
                            String phone, String telephone, String email) {
        customerPage.fillNewCustomerForm(name, refNo, contactPerson, phone, telephone, email);
        customerPage.clickSave();
    }
    public void addCustomer(String name, String phone, String email) {
        customerPage.fillNewCustomerForm(name, null, null, phone, null, email);
        customerPage.clickSave();
    }
    public boolean isCustomerAdded(String name) {
        return customerPage.isCustomerAdded(name);
    }
}

