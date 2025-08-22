package com.example.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {
    private final WebDriver driver;

    private final By firstName = By.id("first-name");
    private final By lastName = By.id("last-name");
    private final By postal = By.id("postal-code");
    private final By continueBtn = By.id("continue");
    private final By finishBtn = By.id("finish");
    private final By completeHeader = By.cssSelector(".complete-header");

    public CheckoutPage(WebDriver driver) { this.driver = driver; }

    public void fillInfoAndContinue(String f, String l, String p) {
        driver.findElement(firstName).sendKeys(f);
        driver.findElement(lastName).sendKeys(l);
        driver.findElement(postal).sendKeys(p);
        driver.findElement(continueBtn).click();
    }

    public void finish() { driver.findElement(finishBtn).click(); }

    public String getCompleteHeader() {
        return driver.findElement(completeHeader).getText();
    }
}
