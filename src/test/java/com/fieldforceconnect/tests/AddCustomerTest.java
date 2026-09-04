package com.fieldforceconnect.tests;

import com.fieldforceconnect.actions.CustomerActions;
import com.fieldforceconnect.actions.LoginActions;
import com.fieldforceconnect.managers.ConfigManager;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AddCustomerTest extends BaseTest {

//    @DataProvider(name = "customerData")
//    public Object[][] customerData() {
//        return new Object[][] {
//                {"Test Customer 1", "9876543210", "testcustomer1@example.com"},
//                {"Test Customer 2", "9876543211", "testcustomer2@example.com"}
//        };
//    }
//
//    @Test(dataProvider = "customerData")
//    public void verifyAddCustomer(
//            String name,
//            String phone,
//            String email) {
//
//        LoginActions loginActions = new LoginActions(driver);
//        CustomerActions customerActions = new CustomerActions(driver);
//
//        loginActions.openApplication(ConfigManager.get("base.url"));
//        loginActions.login(ConfigManager.get("test.username"), ConfigManager.get("test.password"));
//        customerActions.openAddCustomer();
//        customerActions.addCustomer(name, phone, email);
//
//        Assert.assertTrue(
//                customerActions.isCustomerAdded(name),
//                "Customer was not found after adding: " + name
//        );
//    }
@DataProvider(name = "customerData")
public Object[][] customerData() {
    return new Object[][] {
            {
                    "Test Customer 1", "9876543210", "testcustomer1@example.com",
                    "123 Business Park, Sector 62", "Noida", "Uttar Pradesh", "201301"
            },
            {
                    "Test Customer 2", "9876543211", "testcustomer2@example.com",
                    "456 Tech Hub, Hitech City", "Hyderabad", "Telangana", "500081"
            }
    };
}

    @Test(dataProvider = "customerData")
    public void verifyAddCustomer(
            String name, String phone, String email,
            String address, String city, String state, String pincode) {

        LoginActions loginActions = new LoginActions(driver);
        CustomerActions customerActions = new CustomerActions(driver);

        loginActions.openApplication(ConfigManager.get("base.url"));
        loginActions.login(ConfigManager.get("test.username"), ConfigManager.get("test.password"));

        customerActions.openAddCustomer();

        // Updated action invocation matching the expanded structure
        customerActions.addCustomer(name, phone, email);

        Assert.assertTrue(
                customerActions.isCustomerAdded(name),
                "Customer was not found after adding: " + name
        );
    }
}
