package com.cts.policy_bazaar.seleniumutils;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ActionUtil {
    public static void clickAction(WebDriver driver, WebElement ele) {
        Actions act = new Actions(driver);
        act.click(ele).perform();
    }

    public static void sendKeysAction(WebDriver driver, String str) {
        Actions act = new Actions(driver);
        act.sendKeys(str).perform();
    }

    public static void dragAndDropAction(WebDriver driver, WebElement dragSrc, WebElement dragDest) {
        Actions act = new Actions(driver);
        act.dragAndDrop(dragSrc, dragDest).perform();
    }

    public static void rightClickAction(WebDriver driver, WebElement ele) {
        Actions act = new Actions(driver);
        act.contextClick(ele).perform();
    }

    public static void moveToElementAction(WebDriver driver, WebElement ele) {
        Actions act = new Actions(driver);
        act.moveToElement(ele).perform();
    }

}
