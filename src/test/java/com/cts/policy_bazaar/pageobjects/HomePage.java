package com.cts.policy_bazaar.pageobjects;

import com.cts.policy_bazaar.frameworkutils.CommonUtils;
import com.cts.policy_bazaar.seleniumutils.ActionUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{
    Actions actions;
    @FindBy(xpath = "//div[7]//div[@class='shadowHandlerBox']")
    private WebElement travelInsurance;

    @FindBy(xpath = "//p[text()='Car']")
    WebElement carSection;

    @FindBy(xpath = "//ul[@class='ruby-menu']/li[2]/a")
    private WebElement insuranceProduct;

    @FindBy(xpath = "//div[@class='ruby-row']/div[3]/h3")
    private  WebElement healthInsurance;

    public HomePage(WebDriver driver){
        super(driver);
        actions = new Actions(driver);
    }
    public void clickOnTravelInsurance(){
        travelInsurance.click();
    }
    public void clickCarSection() {
        actions.moveToElement(carSection).click().perform();
        driver.manage().deleteAllCookies();
        CommonUtils.sureWait(2);
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


