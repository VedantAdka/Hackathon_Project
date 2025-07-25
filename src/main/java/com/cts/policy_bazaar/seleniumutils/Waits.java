package com.cts.policy_bazaar.seleniumutils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Waits {

    // Sets a global implicit wait time for locating elements
    public static void implicitlyWait(WebDriver driver, int time) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
    }

    // Waits until the given element is clickable and returns it
    public static WebElement waitElementToBeClickable(WebDriver driver, WebElement ele, int time) {
        WebDriverWait wd = new WebDriverWait(driver, Duration.ofSeconds(time));
        return wd.until(ExpectedConditions.elementToBeClickable(ele));
    }

    // Waits until the given element is visible and returns it
    public static WebElement waitElementToBeVisible(WebDriver driver, WebElement ele, int time) {
        WebDriverWait wd = new WebDriverWait(driver, Duration.ofSeconds(time));
        return wd.until(ExpectedConditions.visibilityOf(ele));
    }
}
