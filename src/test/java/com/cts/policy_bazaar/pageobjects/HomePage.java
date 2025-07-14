package com.cts.policy_bazaar.pageobjects;

import com.cts.policy_bazaar.frameworkutils.CommonUtils;
import com.cts.policy_bazaar.seleniumutils.ActionUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{

    @FindBy(xpath = "//div[7]//div[@class='shadowHandlerBox']")
    private WebElement travelInsurance;
    @FindBy(xpath = "//ul[@class='ruby-menu']/li[2]/a")
    private WebElement insuranceProduct;
    @FindBy(xpath = "//div[@class='ruby-row']/div[3]/h3")
    private  WebElement healthInsurance;

    public HomePage(WebDriver driver){
        super(driver);
    }
    public void clickOnTravelInsurance(){
        CommonUtils.sureWait(1);
        travelInsurance.click();
    }
    public boolean insuranceProductsIsEnabled(){
        return insuranceProduct.isEnabled();
    }
    public void hoverToInsuranceProducts(){
        CommonUtils.sureWait(1);
        ActionUtil.moveToElementAction(driver,insuranceProduct);
    }
    public boolean healthInsuranceIsEnabled(){
        return healthInsurance.isEnabled();
    }
    public void selectHealthInsurance(){
        CommonUtils.sureWait(1);
        ActionUtil.moveToElementAction(driver,healthInsurance);
        ActionUtil.clickAction(driver,healthInsurance);
    }

}
