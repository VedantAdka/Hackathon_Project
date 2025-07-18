package com.cts.policy_bazaar.pageobjects;

import com.cts.policy_bazaar.frameworkutils.CommonUtils;
import com.cts.policy_bazaar.seleniumutils.ActionUtil;
import com.cts.policy_bazaar.seleniumutils.SelectUtils;
import com.cts.policy_bazaar.seleniumutils.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Wait;

import java.util.ArrayList;
import java.util.List;

public class PlansPage extends BasePage {

    @FindBy(xpath = "//section[2]/p[@class='newFilterSection__heading']")
    private WebElement showPlansText;
    @FindBy(xpath = "//section[@class='newFilterSection filters']/details[1]/summary")
    private WebElement plansDropDownButton;
    @FindBy(id = "studentTrip")
    private WebElement studentPlanButton;
    @FindBy(xpath = "//div[@class='options_box_wrapper ageOptions']/div[1]/input")
    private WebElement student1;
    @FindBy(xpath = "//div[@class='options_box_wrapper ageOptions']/div[2]/input")
    private WebElement student2;
    @FindBy(id = "feet")
    private WebElement durationDropDown;
    @FindBy(xpath = "//div[@class='pqCtaWrapper']/button")
    private WebElement applyButton;
    @FindBy(xpath = "//div[@class='multiprofileTabs hideSmall']//h2")
    private WebElement showsStudentPlanText;
    @FindBy(xpath = "//p[@class='filter_name_heading']")
    private WebElement sortButton;
    @FindBy(id = "17_sort")
    private WebElement lowToHighButton;
    @FindBy(xpath = "//p[@class='quotesCard--insurerName']")
    private List<WebElement> insuranceCompanyName;
    @FindBy(xpath = "//span[@class='premiumPlanPrice']")
    private List<WebElement> insurancePrice;

    public PlansPage(WebDriver driver) {
        super(driver);
    }

    public boolean plansPageDisplayed(){
        return showPlansText.isDisplayed();
    }
    public void clickOnStudentPlans() {
        if(studentPlanButton.isDisplayed()) {
            CommonUtils.sureWait(2);
            ActionUtil.moveToElementAction(driver, studentPlanButton);
            Waits.waitElementToBeClickable(driver, studentPlanButton, 30);
            ActionUtil.clickAction(driver, studentPlanButton);
            CommonUtils.sureWait(1);
        }
        else{
            CommonUtils.sureWait(2);
            ActionUtil.moveToElementAction(driver, plansDropDownButton);
            Waits.waitElementToBeClickable(driver,plansDropDownButton,30).click();
            CommonUtils.sureWait(2);
            ActionUtil.moveToElementAction(driver, studentPlanButton);
            Waits.waitElementToBeClickable(driver, studentPlanButton, 30);
            ActionUtil.clickAction(driver, studentPlanButton);
            CommonUtils.sureWait(1);
        }
    }

    public void selectBothStudents() {
        student1.click();
        CommonUtils.sureWait(1);
        student2.click();
        CommonUtils.sureWait(1);
    }

    public void selectTripDuration(String duration) {
        Waits.waitElementToBeClickable(driver,durationDropDown,30);
        ActionUtil.moveToElementAction(driver,durationDropDown);
        ActionUtil.clickAction(driver,durationDropDown);
        Actions a=new Actions(driver);
        a.keyDown(Keys.ARROW_DOWN).keyUp(Keys.ARROW_DOWN).keyDown(Keys.ENTER).keyUp(Keys.ENTER).perform();
//        SelectUtils.selectFromText(durationDropDown, duration);
//        CommonUtils.sureWait(1);
    }

    public void clickOnApplyButton() {
        applyButton.click();
    }

    public boolean studentsPlansDisplayed(){
        CommonUtils.sureWait(2);
        return Waits.waitElementToBeVisible(driver,showsStudentPlanText,30).isDisplayed();
    }

    public void clickOnSortDropDownButton() {
        sortButton.click();
        CommonUtils.sureWait(1);
    }

    public void clickOnLowToHighButton() {
        Waits.waitElementToBeClickable(driver,lowToHighButton,30).click();
        CommonUtils.sureWait(1);
    }

    public boolean lowToHighBtnSelected(){
        CommonUtils.sureWait(2);
        return Waits.waitElementToBeVisible(driver,lowToHighButton,30).isSelected();
    }

    public List<String> getInsuranceCompanyName(){
        List<String> companyNames = new ArrayList<>();
        for (int i = 0; i < 3 && i < insuranceCompanyName.size(); i++) {
            companyNames.add(insuranceCompanyName.get(i).getText());
        }
        return companyNames;
    }

    public List<String> getInsurancePrice(){
        List<String> insuranceAmount = new ArrayList<>();
        for (int i = 0; i < 3 && i < insurancePrice.size(); i++) {
            insuranceAmount.add(insurancePrice.get(i).getText());
        }
        return insuranceAmount;
    }
}