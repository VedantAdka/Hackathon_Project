package com.cts.policy_bazaar.pageobjects;

import com.cts.policy_bazaar.frameworkutils.CommonUtils;
import com.cts.policy_bazaar.seleniumutils.ActionUtil;
import com.cts.policy_bazaar.seleniumutils.JavaScriptUtil;
import com.cts.policy_bazaar.seleniumutils.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
    @FindBy(xpath = "//div[@class='MuiPickersDesktopDateRangeCalendar-root']/div[1]//div[@class='MuiPickersDateRangeDay-root']/div/button")
    private List<WebElement> date1List;
    @FindBy(xpath = "//div[@class='MuiPickersDesktopDateRangeCalendar-root']/div[2]//div[@class='MuiPickersDateRangeDay-root']/div/button")
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
    @FindBy(xpath = "//button[text()='Done']")
    private WebElement submitButton;
    @FindBy(className = ".pqCtaWrapper>button")
    private WebElement viewButton;
    @FindBy(xpath = "//span[@class='errorMsg newPq_errorMsg']")
    private WebElement errorMessage;

    public TravelInsurancePage(WebDriver driver){
        super(driver);
    }
    public void putCountryNameInSearchBox(String countryName){
        ActionUtil.moveToElementAction(driver,searchBox);
        ActionUtil.clickAction(driver,searchBox);
        ActionUtil.sendKeysAction(driver,countryName);
        for (WebElement e : countryList) {
            if (e.getText().equalsIgnoreCase(countryName)) {
                e.click();
                break;
            }
        }
    }
    public String getCountryNameSelectedInSearchBox(){
        return countrySelected.getText();
    }
    public void clickOnStartDate(){
        startDate.click();
    }
    public void pickStartDateAndEndDate(String start, String end){
        CommonUtils.sureWait(2);
        for (WebElement e : date1List) {
            if (e.getText().equalsIgnoreCase(start)) {
                e.click();
                break;
            }
        }
        CommonUtils.sureWait(2);
        for (WebElement e : date2List) {
            if (e.getText().equalsIgnoreCase(end)) {
                e.click();
                break;
            }
        }
        Waits.waitElementToBeClickable(driver,doneButton,30);
    }
    public String[] getSelectedStartAndEndDate(){
        String[] str={selectedStartDate.getText(),selectedEndDate.getText()};
        return str;
    }
    public void clickOnAddTraveller(){
        addTravellerButton.click();
    }
    public void clickOnNoOfTraveller(){
        Waits.waitElementToBeClickable(driver,noOfTraveller,30);
    }
    public void selectAgeOfFirstStudent(String age1){
        ageOfTraveller1DropDownButton.click();
        for (WebElement age : ageList) {
            if (age.getText().contains(age1)) {
                age.click();
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
                break;
            }
        }
    }
    public void clickOnNoButton(){
        noButton.click();
    }
    public void clickOnSubmitButton(){
        submitButton.click();
    }
    public void clickOnViewPlansButton(){
        JavaScriptUtil.JSscrollToElement(viewButton,driver);
        JavaScriptUtil.JSclick(viewButton,driver);
    }
    public String getErrorMessage(){
        return errorMessage.getText();
    }
    public void clickCutButton(){
        ActionUtil.moveToElementAction(driver,cutButton);
        ActionUtil.clickAction(driver,cutButton);
    }

}
