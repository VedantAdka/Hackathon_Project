package com.cts.policy_bazaar.pageobjects;

import com.cts.policy_bazaar.seleniumutils.SelectUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class PlansPage extends BasePage {

    @FindBy(xpath = "//section[2]/p[@class='newFilterSection__heading']")
    private WebElement showPlansText;
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
        studentPlanButton.click();
    }

    public void selectBothStudents() {
        student1.click();
        student2.click();
    }

    public void selectTripDuration(String duration) {
        SelectUtils.selectFromText(durationDropDown, duration);
    }

    public void clickOnApplyButton() {
        applyButton.click();
    }

    public boolean studentsPlansDisplayed(){
        return showsStudentPlanText.isDisplayed();
    }

    public void clickOnSortDropDownButton() {
        sortButton.click();
    }

    public void clickOnLowToHighButton() {
        lowToHighButton.click();
    }

    public boolean lowToHighBtnSelected(){
        return lowToHighButton.isSelected();
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
