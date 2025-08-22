package com.example.saucedemo.tests;

import com.example.saucedemo.core.BaseTest;
import com.example.saucedemo.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PurchaseFlowTest extends BaseTest {

    @Test
    public void buyOneItem_endToEnd() {
        // Login
        LoginPage login = new LoginPage(getDriver());
        login.open();
        login.login("standard_user", "secret_sauce");

        // Add to cart
        InventoryPage inv = new InventoryPage(getDriver());
        Assert.assertTrue(inv.isLoaded(), "Inventory not loaded.");
        inv.addBackpackToCart();
        Assert.assertEquals(inv.cartCount(), "1", "Cart count should be 1 after add.");
        inv.goToCart();

        // Checkout
        CartPage cart = new CartPage(getDriver());
        cart.checkout();

        CheckoutPage co = new CheckoutPage(getDriver());
        co.fillInfoAndContinue("John", "Doe", "12345");
        co.finish();

        // Verify
        String header = co.getCompleteHeader();
        Assert.assertTrue(header.toUpperCase().contains("THANK YOU"),
                "Order completion message not found. Actual: " + header);
    }
}
