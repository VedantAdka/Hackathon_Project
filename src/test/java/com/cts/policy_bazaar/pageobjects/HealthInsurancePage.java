package com.cts.policy_bazaar.pageobjects;

import com.cts.policy_bazaar.frameworkutils.CommonUtils;
import com.cts.policy_bazaar.seleniumutils.ActionUtil;
import com.cts.policy_bazaar.seleniumutils.JavaScriptUtil;
import com.cts.policy_bazaar.seleniumutils.Waits;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class HealthInsurancePage extends BasePage{

    @FindBy(xpath = "//div[@class='close-st-footer strip_close']")
    private WebElement closeBtn;
    @FindBy(xpath = "//div[@class='seeMorePlans']")
    private WebElement viewMorePlansBtn;
    @FindBy(xpath = "//div[@class='view-more-card-plans']")
    private List<WebElement>  listOfViewMorePlans;
    @FindBy(xpath = "//div[@class='plan-name']")
    private List<WebElement> insuranceListNames;
    @FindBy(xpath = "//div[@class='cover-price']/div[@class='label-value'][1]/div[2]")
    private List<WebElement> coverAmountList;
    @FindBy(xpath = "//div[@class='cover-price']/div[@class='label-value'][2]/div[2]")
    private List<WebElement> startAtAmountList;

    public HealthInsurancePage(WebDriver driver){
        super(driver);
    }
    public void clickOnCloseButton(){
        JavaScriptUtil.JSscrollToElement(closeBtn,driver);
        CommonUtils.sureWait(2);
        JavaScriptUtil.JSclick(closeBtn,driver);
    }
    public void clickOnViewMorePlansButton(){
        CommonUtils.sureWait(1);
        ActionUtil.moveToElementAction(driver,viewMorePlansBtn);
        CommonUtils.sureWait(1);
        ActionUtil.clickAction(driver,viewMorePlansBtn);
    }
    public void clickOnListOfViewMorePlansButtons(){
        for(WebElement i:listOfViewMorePlans){
            Waits.waitElementToBeVisible(driver,i,30);
            ActionUtil.moveToElementAction(driver,i);
            Waits.waitElementToBeClickable(driver,i,30).click();
        }
    }
    public List<String> getInsuranceNames(){
        List<String> insuranceNames=new ArrayList<>();
        for(WebElement i: insuranceListNames){
            insuranceNames.add(i.getText());
        }
        return insuranceNames;
    }
    public List<String> getCoverAmount(){
        List<String> coverAmount=new ArrayList<>();
        for(WebElement i: coverAmountList){
            coverAmount.add(i.getText());
        }
        return coverAmount;
    }
    public List<String> getStartAtAmount(){
        List<String> startAtAmount =new ArrayList<>();
        for(WebElement i: startAtAmountList){
            startAtAmount.add(i.getText());
        }
        return startAtAmount;
    }
}
