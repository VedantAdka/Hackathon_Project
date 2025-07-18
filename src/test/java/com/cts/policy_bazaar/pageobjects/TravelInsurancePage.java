package com.cts.policy_bazaar.pageobjects;

import com.cts.policy_bazaar.frameworkutils.CommonUtils;
import com.cts.policy_bazaar.seleniumutils.ActionUtil;
import com.cts.policy_bazaar.seleniumutils.JavaScriptUtil;
import com.cts.policy_bazaar.seleniumutils.Waits;
import io.cucumber.java.en_old.Ac;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Wait;

import java.util.List;

public class TravelInsurancePage extends BasePage{
    @FindBy(xpath = "//input[@id='country']")
    private WebElement searchBox;
    @FindBy(xpath = "//ul[@class='search-list']/li")
    private List<WebElement> countryList;
    @FindBy(xpath = "//div[@class='selectedCountryWrap']/p")
    private WebElement countrySelected;
    @FindBy(xpath = "//article[@class='newPq_duration_wrap']//div[1]")
    private WebElement startDate;
    @FindBy(xpath = "//div[@class='MuiPickersDesktopDateRangeCalendar-root']/div[1]//div[@class='MuiPickersDateRangeDay-root']/div/button/span[1]")
    private List<WebElement> date1List;
    @FindBy(xpath = "//div[@class='MuiPickersDesktopDateRangeCalendar-root']/div[2]//div[@class='MuiPickersDateRangeDay-root']/div/button/span[1]")
    private List<WebElement> date2List;
    @FindBy(xpath = "//*[@id=\"modal-root\"]/section/article/div/div/div[2]/div[3]/div/button")
    private WebElement doneButton;
    @FindBy(xpath = "//div[@class='newPq_duration_wrap__dateCol'][1]/p/em")
    private WebElement selectedStartDate;
    @FindBy(xpath = "//div[@class='newPq_duration_wrap__dateCol'][2]/p/em")
    private WebElement selectedEndDate;
    @FindBy(xpath = "//section/section[2]/article[3]")
    private WebElement addTravellerButton;
    @FindBy(xpath = "//a[@class='newPq_modal__close']")
    private WebElement cutButton;
    @FindBy(xpath = "//div/label[@for='traveller_2']")
    private WebElement noOfTraveller;
    @FindBy(xpath = "//div[@id='0']/div[@id=\"divarrow_undefined\"]/div")
    private WebElement ageOfTraveller1DropDownButton;
    @FindBy(xpath = "//div[@class='options_box_wrapper__option']/label")
    private List<WebElement> ageList;
    @FindBy(xpath = "//div[@id='1']/div[@id=\"divarrow_undefined\"]/div")
    private WebElement ageOfTraveller2DropDownButton;
    @FindBy(id="ped_no")
    private WebElement noButton;
    @FindBy(xpath = "//section[@id='modal-root']//div[@class='pqCtaWrapper']/button")
    private WebElement submitButton;
    @FindBy(xpath = "//article[@class='newPq_travellers_wrap']/p")
    private WebElement noOfTravellerMsg;
    @FindBy(xpath = "//div[@class='pqCtaWrapper']/button")
    private WebElement viewButton;
    @FindBy(xpath = "//span[@class='errorMsg newPq_errorMsg']")
    private WebElement errorMessage;

    public TravelInsurancePage(WebDriver driver){
        super(driver);
    }
    public boolean putCountryNameInSearchBox(String countryName){
        CommonUtils.sureWait(2);
        Waits.waitElementToBeClickable(driver,searchBox,30);
        ActionUtil.moveToElementAction(driver,searchBox);
        ActionUtil.clickAction(driver,searchBox);
        ActionUtil.sendKeysAction(driver,countryName);
        for (WebElement e : countryList) {
            if (e.getText().equalsIgnoreCase(countryName)) {
                e.click();
                break;
            }
            else if(e.getText().equalsIgnoreCase("No result found")){
                return false;
            }
        }
        return true;
    }
    public String getCountryNameSelectedInSearchBox(){
        return countrySelected.getText();
    }
    public void clickOnStartDate(){
        CommonUtils.sureWait(2);
        ActionUtil.moveToElementAction(driver,startDate);
        ActionUtil.clickAction(driver,startDate);
    }
    public void pickStartDateAndEndDate(String start, String end){
        CommonUtils.sureWait(2);
        for (WebElement e : date1List) {
            if (e.getText().equalsIgnoreCase(start)) {
                Waits.waitElementToBeClickable(driver,e,30).click();
                break;
            }
        }
        CommonUtils.sureWait(2);
        for (WebElement e : date2List) {
            if (e.getText().equalsIgnoreCase(end)) {
                Waits.waitElementToBeClickable(driver,e,30).click();
                break;
            }
        }
        CommonUtils.sureWait(2);
        Waits.waitElementToBeClickable(driver,doneButton,30).click();
    }
    public String[] getSelectedStartAndEndDate(){
        String[] str={selectedStartDate.getText(),selectedEndDate.getText()};
        return str;
    }
    public void clickOnAddTraveller(){
        CommonUtils.sureWait(2);
        ActionUtil.moveToElementAction(driver,addTravellerButton);
        ActionUtil.clickAction(driver,addTravellerButton);
    }
    public void clickOnNoOfTraveller(){
        CommonUtils.sureWait(2);
        ActionUtil.moveToElementAction(driver,noOfTraveller);
        ActionUtil.clickAction(driver,noOfTraveller);
    }
    public void selectAgeOfFirstStudent(String age1){
        ageOfTraveller1DropDownButton.click();
        for (WebElement age : ageList) {
            if (age.getText().contains(age1)) {
                age.click();
                CommonUtils.sureWait(1);
                break;
            }
        }
    }
    public void selectAgeOfSecondStudent(String age2){
        ActionUtil.moveToElementAction(driver,ageOfTraveller2DropDownButton);
        ActionUtil.clickAction(driver,ageOfTraveller2DropDownButton);
        for (WebElement age : ageList) {
            if (age.getText().contains(age2)) {
                age.click();
                CommonUtils.sureWait(1);
                break;
            }
        }
    }
    public void clickOnNoButton(){
        noButton.click();
        CommonUtils.sureWait(2);
    }
    public void clickOnSubmitButton(){
        submitButton.click();
        CommonUtils.sureWait(2);
    }
    public String getNoOfTravellerMsg(){
        return noOfTravellerMsg.getText();
    }
    public void clickOnViewPlansButton(){
        JavaScriptUtil.JSscrollToElement(viewButton,driver);
        CommonUtils.sureWait(1);
        JavaScriptUtil.JSclick(viewButton,driver);
    }
    public String getErrorMessage(){
        CommonUtils.sureWait(2);
        return Waits.waitElementToBeVisible(driver,errorMessage,30).getText();
    }
    public void clickCutButton(){
        ActionUtil.moveToElementAction(driver,cutButton);
        ActionUtil.clickAction(driver,cutButton);
    }

}