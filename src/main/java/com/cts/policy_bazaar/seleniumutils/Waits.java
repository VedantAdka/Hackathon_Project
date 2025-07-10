package com.cts.policy_bazaar.seleniumutils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Waits {
    public static void implicitlyWait(WebDriver driver, int time) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
    }

    public static WebElement waitElementToBeClickable(WebDriver driver, WebElement ele, int time) {
        WebDriverWait wd = new WebDriverWait(driver, Duration.ofSeconds(time));
        return wd.until(ExpectedConditions.elementToBeClickable(ele));
    }

    public static WebElement waitElementToBeVisible(WebDriver driver, WebElement ele, int time) {
        WebDriverWait wd = new WebDriverWait(driver, Duration.ofSeconds(time));
        return wd.until(ExpectedConditions.visibilityOf(ele));
    }
}
