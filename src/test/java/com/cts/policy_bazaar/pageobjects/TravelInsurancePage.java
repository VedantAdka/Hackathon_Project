package com.cts.policy_bazaar.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TravelInsurancePage extends BasePage{

    @FindBy(xpath = "//section/section[2]/article[3]")
    private WebElement addTravellerButton;
    @FindBy(xpath = "//div/label[@for='traveller_2']")
    private WebElement noOfTraveller;
    @FindBy(xpath = "//div[@id='0']/div[@id=\"divarrow_undefined\"]/div")
    private WebElement ageOfTraveller1DropDownButton;
    @FindBy(xpath = "//div[@id='1']/div[@id=\"divarrow_undefined\"]/div")
    private WebElement ageOfTraveller2DropDownButton;

    public TravelInsurancePage(WebDriver driver){
        super(driver);
    }
    public void clickOnAddTraveller(){
        addTravellerButton.click();
    }
    public void clickOnNoOfTraveller(){
        noOfTraveller.click();
    }
    public void clickOnTraveller1AgeDropDown(){
        ageOfTraveller1DropDownButton.click();
    }
    public void clickOnTraveller2AgeDropDown(){
        ageOfTraveller2DropDownButton.click();
    }
    

}
