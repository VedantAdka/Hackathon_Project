package com.cts.policy_bazaar.seleniumutils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtil {

    // Clicks on an element using JavaScript
    public static void JSclick(WebElement ele, WebDriver driver) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click();", ele);
    }

    // Sends text input to an element using JavaScript
    public static void JSsendKeys(WebElement ele, WebDriver driver, String str) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].value='" + str + "';", ele);
    }

    // Scrolls the page until the element is in view
    public static void JSscrollToElement(WebElement ele, WebDriver driver) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView(true);", ele);
    }

    // Scrolls the page by specified x and y pixel values
    public static void JSscrollBy(WebDriver driver, int x, int y) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(" + x + "," + y + ")");
    }

    // Scrolls to the bottom of the page
    public static void JSscrollTillPageEnd(WebDriver driver) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    // Scrolls to the top of the page
    public static void JSscrollToTopPosition(WebDriver driver) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,-document.body.scrollHeight)");
    }
}
