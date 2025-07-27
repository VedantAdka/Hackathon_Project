package com.cts.policy_bazaar.seleniumutils;


import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ActionUtil {

    // Performs a click action on the given element
    public static void clickAction(WebDriver driver, WebElement ele) {
        Actions act = new Actions(driver);
        act.click(ele).perform();
    }

    // Sends keyboard input using Actions class
    public static void sendKeysAction(WebDriver driver, String str) {
        Actions act = new Actions(driver);
        act.sendKeys(str).perform();
    }

    // Performs drag-and-drop from source to destination element
    public static void dragAndDropAction(WebDriver driver, WebElement dragSrc, WebElement dragDest) {
        Actions act = new Actions(driver);
        act.dragAndDrop(dragSrc, dragDest).perform();
    }

    // Performs right-click (context click) on the given element
    public static void rightClickAction(WebDriver driver, WebElement ele) {
        Actions act = new Actions(driver);
        act.contextClick(ele).perform();
    }

    // Moves the mouse pointer to the specified element
    public static void moveToElementAction(WebDriver driver, WebElement ele) {
        Actions act = new Actions(driver);
        act.moveToElement(ele).perform();
    }

    //Performs the Key Press Action
    public static void pressKey(WebDriver driver, Keys key){
        Actions act=new Actions(driver);
        act.keyDown(key).perform();
    }

    //Performs the Key Release Action
    public static void releaseKey(WebDriver driver, Keys key){
        Actions act=new Actions(driver);
        act.keyUp(key).perform();
    }



}
