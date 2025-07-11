package com.cts.policy_bazaar.seleniumutils;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class SelectUtils {
    public static void selectFromValue(WebElement ele, String value){
        Select select=new Select(ele);
        select.selectByValue(value);
    }
    public static void selectFromText(WebElement ele, String text){
        Select select=new Select(ele);
        select.selectByVisibleText(text);
    }
    public static void selectFromIndex(WebElement ele, int index){
        Select select=new Select(ele);
        select.selectByIndex(index);
    }
    public static List<WebElement> getDropDownOptions(WebElement ele){
        Select select=new Select(ele);
        return select.getOptions();
    }

}
