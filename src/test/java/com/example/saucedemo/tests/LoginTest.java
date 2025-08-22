package com.example.saucedemo.tests;

import com.example.saucedemo.core.BaseTest;
import com.example.saucedemo.pages.LoginPage;
import com.example.saucedemo.pages.InventoryPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void validLogin_shouldNavigateToInventory() {
        LoginPage login = new LoginPage(getDriver());
        login.open();
        login.login("standard_user", "secret_sauce");
        InventoryPage inv = new InventoryPage(getDriver());
        Assert.assertTrue(inv.isLoaded(), "Inventory page didn't load after login.");
    }

    @Test
    public void invalidLogin_shouldShowError() {
        LoginPage login = new LoginPage(getDriver());
        login.open();
        login.login("standard_user", "wrong_pass");
        String error = login.getErrorText();
        Assert.assertTrue(error.toLowerCase().contains("epic sadface"),
                "Expected error message not shown. Actual: " + error);

        System.out.println("CI Test Run Well !");

    }

}
