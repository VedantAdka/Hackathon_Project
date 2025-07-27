package com.cts.policy_bazaar.pageobjects;

import com.cts.policy_bazaar.frameworkutils.CommonUtils;
import com.cts.policy_bazaar.seleniumutils.ActionUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{

    @FindBy(xpath = "//div[7]//div[@class='shadowHandlerBox']")
    private WebElement travelInsurance;

    @FindBy(xpath = "//p[text()='Car']")
    WebElement carSection;

    @FindBy(xpath = "//ul[@class='ruby-menu']/li[2]/a")
    private WebElement insuranceProduct;

    @FindBy(xpath = "//div[@class='ruby-row']/div[3]/h3")
    private  WebElement healthInsurance;

    // Constructor initializes WebDriver
    public HomePage(WebDriver driver){
        super(driver);
    }

    // Clicks on the Travel Insurance section
    public void clickOnTravelInsurance(){
        travelInsurance.click();
    }

    // Clicks on the Car section and clears cookies
    public void clickCarSection() {
        ActionUtil.moveToElementAction(driver,carSection);
        ActionUtil.clickAction(driver,carSection);
        driver.manage().deleteAllCookies();
        CommonUtils.sureWait(2);
    }

    // Checks if the Insurance Products menu is enabled
    public boolean insuranceProductsIsEnabled(){
        return insuranceProduct.isEnabled();
    }

    // Hovers over the Insurance Products menu
    public void hoverToInsuranceProducts(){
        CommonUtils.sureWait(1);
        ActionUtil.moveToElementAction(driver,insuranceProduct);
    }

    // Checks if the Health Insurance option is enabled
    public boolean healthInsuranceIsEnabled(){
        return healthInsurance.isEnabled();
    }

    // Selects the Health Insurance option
    public void selectHealthInsurance(){
        CommonUtils.sureWait(1);
        ActionUtil.moveToElementAction(driver,healthInsurance);
        ActionUtil.clickAction(driver,healthInsurance);
    }
}


