package com.cts.policy_bazaar.pageobjects;

import com.cts.policy_bazaar.seleniumutils.SelectUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class PlansPage extends BasePage{

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
    @FindBy(xpath = "//p[@class='filter_name_heading']")
    private WebElement sortButton;
    @FindBy(id = "17_sort")
    private WebElement lowToHighButton;
    @FindBy

    public PlansPage(WebDriver driver){
        super(driver);
    }
    public void clickOnStudentPlans(){
        studentPlanButton.click();
    }
    public void selectBothStudents(){
        student1.click();
        student2.click();
    }
    public void selectTripDuration(String duration){
        SelectUtils.selectFromText(durationDropDown,duration);
    }
    public void clickOnApplyButton(){
        applyButton.click();
    }
    public void clickOnSortDropDownButton(){
        sortButton.click();
    }
    public void clickOnLowToHighButton(){
        lowToHighButton.click();
    }
}
