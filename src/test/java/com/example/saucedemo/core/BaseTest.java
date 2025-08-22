package com.example.saucedemo.core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

public class BaseTest {
    private static final ThreadLocal<WebDriver> TL_DRIVER = new ThreadLocal<>();

    @BeforeClass(alwaysRun = true)
    public void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod(alwaysRun = true)
    public void setupMethod() {
        ChromeOptions opts = new ChromeOptions();
        boolean headless = Boolean.parseBoolean(System.getProperty("headless", "false"))
                || System.getenv("CI") != null;
        if (headless) {
            opts.addArguments("--headless=new");
            opts.addArguments("--no-sandbox", "--disable-dev-shm-usage");
            opts.addArguments("--window-size=1920,1080");
        }
        TL_DRIVER.set(new ChromeDriver(opts));
        getDriver().manage().window().maximize();
    }

    protected WebDriver getDriver() {
        return TL_DRIVER.get();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (getDriver() != null) {
            getDriver().quit();
            TL_DRIVER.remove();
        }
    }
}
