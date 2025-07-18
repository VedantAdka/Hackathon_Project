package com.cts.policy_bazaar.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

abstract public class BasePage {
    protected WebDriver driver;
    protected BasePage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    public String getTitle(){
        return driver.getTitle();
    }
}
