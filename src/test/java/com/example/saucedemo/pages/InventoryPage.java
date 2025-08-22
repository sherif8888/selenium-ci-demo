package com.example.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage {
    private final WebDriver driver;
    private final By title = By.cssSelector(".title");
    private final By cartLink = By.cssSelector(".shopping_cart_link");
    private final By cartBadge = By.cssSelector(".shopping_cart_badge");

    private final By addBackpack = By.id("add-to-cart-sauce-labs-backpack");

    public InventoryPage(WebDriver driver) { this.driver = driver; }

    public boolean isLoaded() {
        return !driver.findElements(title).isEmpty();
    }

    public void addBackpackToCart() {
        driver.findElement(addBackpack).click();
    }

    public String cartCount() {
        return driver.findElements(cartBadge).isEmpty()
                ? "0" : driver.findElement(cartBadge).getText();
    }

    public void goToCart() {
        driver.findElement(cartLink).click();
    }
}
