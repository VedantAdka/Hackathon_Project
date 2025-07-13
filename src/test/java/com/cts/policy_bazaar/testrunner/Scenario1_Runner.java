package com.cts.policy_bazaar.testrunner;

import com.cts.policy_bazaar.browserutils.BrowserFactory;
import com.cts.policy_bazaar.frameworkutils.CommonUtils;
import com.cts.policy_bazaar.frameworkutils.PropertiesFileReader;
import com.cts.policy_bazaar.pageobjects.HomePage;
import com.cts.policy_bazaar.pageobjects.PlansPage;
import com.cts.policy_bazaar.pageobjects.TravelInsurancePage;
import com.cts.policy_bazaar.seleniumutils.ScreenShotUtil;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class Scenario1_Runner {
    public static WebDriver driver;
    HomePage hp=null;
    TravelInsurancePage tp=null;
    PlansPage pp=null;
    String bn = null;
    String wr = null;
    String url = null;
    String remoteip = null;
    @BeforeMethod
    public void init() {
        try {
            bn = PropertiesFileReader.getPropertyValue("config", "browsername");
            wr = PropertiesFileReader.getPropertyValue("config", "wheretorun");
            remoteip = PropertiesFileReader.getPropertyValue("config", "hubip");
            url = PropertiesFileReader.getPropertyValue("config", "url");
            driver = BrowserFactory.getBrowser(bn, wr, remoteip);
            BrowserFactory.OpenUrl(url);
            hp=new HomePage(driver);
            tp=new TravelInsurancePage(driver);
            pp=new PlansPage(driver);
        } catch (Exception e) {
            ScreenShotUtil.takeScreenShot(driver);
            e.printStackTrace();
        }
    }

    @Test(priority = 0)
    public void validateAccessingTravelInsurancePage(){
        hp.clickOnTravelInsurance();
        String actual=driver.getTitle();
        String expected=hp.getTitle();
        Assert.assertEquals(actual,expected,"Title does not match");
    }
    @Test(priority = 1,dependsOnMethods = {"validateAccessingTravelInsurancePage"})
    public void validateSelectingDestination(){
        tp.putCountryNameInSearchBox("Germany");
        String actual=tp.getCountryNameSelectedInSearchBox();
        String expected="Germany";
        Assert.assertEquals(actual,expected,"Wrong country selected for destination");
    }
    @Test(dependsOnMethods = {"validateSelectingDestination"})
    public void validateTravelStartAndEndDate(){
        tp.clickOnStartDate();
        tp.pickStartDateAndEndDate("12","10");
        String actual=tp.getSelectedStartAndEndDate()[0];
        String expected="12";
        Assert.assertEquals(actual,expected,"Wrong Dates selected");
    }
    @Test(dependsOnMethods = {"validateTravelStartAndEndDate"})
    public void validateSelecting2TravellersAndGoingToPlansPage(){
        tp.clickOnNoOfTraveller();
        tp.selectAgeOfFirstStudent("22");
        tp.selectAgeOfSecondStudent("21");
        tp.clickOnNoButton();
        tp.clickOnSubmitButton();
        tp.clickOnViewPlansButton();
        Assert.assertTrue(pp.plansPageDisplayed());
    }
    @Test(dependsOnMethods = {"validateSelecting2TravellersAndGoingToPlansPage"})
    public void validateSelectingStudentPlans(){
        pp.clickOnStudentPlans();
        pp.selectBothStudents();
        pp.selectTripDuration("30 Days");
        pp.clickOnApplyButton();
        Assert.assertTrue(pp.studentsPlansDisplayed());
    }
    @Test(dependsOnMethods = {"validateSelectingStudentPlans"})
    public void validatedSortingPlansFromLowToHigh(){
        pp.clickOnSortDropDownButton();
        pp.clickOnLowToHighButton();
        Assert.assertTrue(pp.lowToHighBtnSelected(),"");
    }
    @Test(dependsOnMethods = {"validatedSortingPlansFromLowToHigh"})
    public void validateGettingTop3Plans(){
        List<String> insuranceCompanyName=pp.getInsuranceCompanyName();
        List<String> insuranceAmount=pp.getInsurancePrice();
        int actual=insuranceAmount.size();
        Assert.assertTrue(actual!=0,"Did not get any plans");
    }
    @Test(dependsOnMethods = {"validateTravelStartAndEndDate"})
    public void validateNoTravellerSelectedGivesError(){
        tp.clickCutButton();
        tp.clickOnViewPlansButton();
        String actual=tp.getErrorMessage();
        String expected="Please add traveller(s)";
        Assert.assertEquals(actual,expected,"Did not throw error");
        ScreenShotUtil.takeScreenShot(driver,"validateNoTravellerSelectedGivesError");
    }
    @Test(dependsOnMethods = {"validateAccessingTravelInsurancePage"})
    public void validateInvalidCountryNameShowsNoResult(){
        boolean res=tp.putCountryNameInSearchBox("ZZZ");
        Assert.assertTrue(res,"Did not throw error");
        ScreenShotUtil.takeScreenShot(driver,"validateInvalidCountryNameShowsNoResult");
    }
    @Test(dependsOnMethods = {"validateSelectingDestination"})
    public void validateNotSelectingDateThrowsError(){
        tp.clickOnAddTraveller();
        tp.clickOnNoOfTraveller();
        tp.selectAgeOfFirstStudent("22");
        tp.selectAgeOfSecondStudent("21");
        tp.clickOnNoButton();
        tp.clickOnSubmitButton();
        tp.clickCutButton();
        tp.clickOnViewPlansButton();
        String actual=tp.getErrorMessage();
        String expected="Please select trip dates";
        Assert.assertEquals(actual,expected,"Did not throw error");
        ScreenShotUtil.takeScreenShot(driver,"validateNotSelectingDateThrowsError");
    }
    @AfterMethod
    public void end(){
        CommonUtils.sureWait(3);
        driver.quit();
    }

}
