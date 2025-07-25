package com.cts.policy_bazaar.seleniumutils;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class SelectUtils {

    // Selects an option from dropdown using the value attribute
    public static void selectFromValue(WebElement ele, String value){
        Select select=new Select(ele);
        select.selectByValue(value);
    }

    // Selects an option from dropdown using visible text
    public static void selectFromText(WebElement ele, String text){
        Select select=new Select(ele);
        select.selectByVisibleText(text);
    }

    // Selects an option from dropdown using index
    public static void selectFromIndex(WebElement ele, int index){
        Select select=new Select(ele);
        select.selectByIndex(index);
    }

    // Returns all available options from the dropdown
    public static List<WebElement> getDropDownOptions(WebElement ele){
        Select select=new Select(ele);
        return select.getOptions();
    }

}
