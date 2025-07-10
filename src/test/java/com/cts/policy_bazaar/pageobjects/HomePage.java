package com.cts.policy_bazaar.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{
    @FindBy(xpath = "//div[7]//div[@class='shadowHandlerBox']")
    private WebElement travelInsurance;
    public HomePage(WebDriver driver){
        super(driver);
    }
    public void clickOnTravelInsurance(){
        travelInsurance.click();
    }
}
