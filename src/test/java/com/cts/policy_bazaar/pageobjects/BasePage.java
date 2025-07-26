package com.cts.policy_bazaar.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

abstract public class BasePage {
    protected WebDriver driver;

    // Constructor initializes WebDriver and page elements using PageFactory
    protected BasePage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    // Returns the current page title
    public String getTitle(){
        return driver.getTitle();
    }
}
