package com.cts.policy_bazaar.testrunner;
import com.cts.policy_bazaar.browserutils.BrowserFactory;
import com.cts.policy_bazaar.frameworkutils.CommonUtils;
import com.cts.policy_bazaar.frameworkutils.PropertiesFileReader;
import com.cts.policy_bazaar.frameworkutils.ReadAndWriteFromExcel;
import com.cts.policy_bazaar.pageobjects.HomePage;
import com.cts.policy_bazaar.pageobjects.PlansPage;
import com.cts.policy_bazaar.pageobjects.TravelInsurancePage;
import com.cts.policy_bazaar.seleniumutils.ScreenShotUtil;
import com.cts.policy_bazaar.testlistener.MyListener;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

@Listeners(MyListener.class)
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
            hp.clickOnTravelInsurance();
        } catch (Exception e) {
            ScreenShotUtil.takeScreenShot(driver);
            e.printStackTrace();
        }
    }

    @Test(priority = 0)
    public void validateAccessingTravelInsurancePage(){
        String actual=driver.getTitle();
        String expected=hp.getTitle();
        Assert.assertEquals(actual,expected,"Title does not match");
    }
    @Test(priority = 1,dataProvider = "excelTestData",dataProviderClass = ReadAndWriteFromExcel.class)
    public void validateSelectingDestination(String country){
        CommonUtils.sureWait(3);
        tp.putCountryNameInSearchBox(country);
        String actual=tp.getCountryNameSelectedInSearchBox();
        String expected=country;
        Assert.assertEquals(actual,expected,"Wrong country selected for destination");
    }
    @Test(priority = 2,dataProvider = "excelTestData",dataProviderClass = ReadAndWriteFromExcel.class)
    public void validateTravelStartAndEndDate(String startDate,String endDate){
        tp.clickOnStartDate();
        tp.pickStartDateAndEndDate(startDate,endDate);
        String actual=tp.getSelectedStartAndEndDate()[0];
        String expected=startDate;
        Assert.assertTrue(actual.contains(expected),"Wrong Dates selected");
    }
    @Test(priority = 3,dataProvider = "excelTestData",dataProviderClass = ReadAndWriteFromExcel.class)
    public void validateSelecting2TravellersAndGoingToPlansPage(String age1,String age2,String message){
        tp.clickOnAddTraveller();
        tp.clickOnNoOfTraveller();
        tp.selectAgeOfFirstStudent(age1);
        tp.selectAgeOfSecondStudent(age2);
        tp.clickOnNoButton();
        tp.clickOnSubmitButton();
        String actual=tp.getNoOfTravellerMsg();
        String expected=message;
        Assert.assertEquals(actual,expected,"2 Travellers not selected");
    }
    @Test(priority = 4,dataProvider = "excelTestData",dataProviderClass = ReadAndWriteFromExcel.class)
    public void validateClickingOnViewPlansAndGoingToPlansPage(String country,String startDate,String endDate,String age1,String age2){
        CommonUtils.sureWait(3);
        tp.putCountryNameInSearchBox(country);
        tp.clickOnStartDate();
        tp.pickStartDateAndEndDate(startDate,endDate);
        tp.clickOnNoOfTraveller();
        tp.selectAgeOfFirstStudent(age1);
        tp.selectAgeOfSecondStudent(age2);
        tp.clickOnNoButton();
        tp.clickOnSubmitButton();
        tp.clickOnViewPlansButton();
        Assert.assertTrue(pp.plansPageDisplayed(),"Did not switch to Plans Page");
    }
    @Test(priority = 5,dataProvider = "excelTestData",dataProviderClass = ReadAndWriteFromExcel.class)
    public void validateSelectingStudentPlans(String country,String startDate,String endDate,String age1,String age2,String duration){
        CommonUtils.sureWait(3);
        tp.putCountryNameInSearchBox(country);
        tp.clickOnStartDate();
        tp.pickStartDateAndEndDate(startDate,endDate);
        tp.clickOnNoOfTraveller();
        tp.selectAgeOfFirstStudent(age1);
        tp.selectAgeOfSecondStudent(age2);
        tp.clickOnNoButton();
        tp.clickOnSubmitButton();
        tp.clickOnViewPlansButton();
        pp.clickOnStudentPlans();
        pp.selectBothStudents();
        pp.selectTripDuration(duration);
        pp.clickOnApplyButton();
        Assert.assertTrue(pp.studentsPlansDisplayed(),"Did not get selected");
    }
    @Test(priority = 6,dataProvider = "excelTestData",dataProviderClass = ReadAndWriteFromExcel.class)
    public void validatedSortingPlansFromLowToHigh(String country,String startDate,String endDate,String age1,String age2,String duration){
        CommonUtils.sureWait(3);
        tp.putCountryNameInSearchBox(country);
        tp.clickOnStartDate();
        tp.pickStartDateAndEndDate(startDate,endDate);
        tp.clickOnNoOfTraveller();
        tp.selectAgeOfFirstStudent(age1);
        tp.selectAgeOfSecondStudent(age2);
        tp.clickOnNoButton();
        tp.clickOnSubmitButton();
        tp.clickOnViewPlansButton();
        pp.clickOnStudentPlans();
        pp.selectBothStudents();
        pp.selectTripDuration(duration);
        pp.clickOnApplyButton();
        pp.clickOnSortDropDownButton();
        pp.clickOnLowToHighButton();
        Assert.assertTrue(pp.lowToHighBtnSelected(),"Did not get sorted");
    }
    @Test(priority = 7,dataProvider = "excelTestData",dataProviderClass = ReadAndWriteFromExcel.class)
    public void validateGettingTop3Plans(String country,String startDate,String endDate,String age1,String age2,String duration){
        CommonUtils.sureWait(3);
        tp.putCountryNameInSearchBox(country);
        tp.clickOnStartDate();
        tp.pickStartDateAndEndDate(startDate,endDate);
        tp.clickOnNoOfTraveller();
        tp.selectAgeOfFirstStudent(age1);
        tp.selectAgeOfSecondStudent(age2);
        tp.clickOnNoButton();
        tp.clickOnSubmitButton();
        tp.clickOnViewPlansButton();
        pp.clickOnStudentPlans();
        pp.selectBothStudents();
        pp.selectTripDuration(duration);
        pp.clickOnApplyButton();
        pp.clickOnSortDropDownButton();
        pp.clickOnLowToHighButton();
        List<String> insuranceCompanyName=pp.getInsuranceCompanyName();
        List<String> insuranceAmount=pp.getInsurancePrice();
        int actual=insuranceAmount.size();
        Assert.assertTrue(actual!=0,"Did not get any plans");
    }
    @Test(priority = 8,dataProvider = "excelTestData",dataProviderClass = ReadAndWriteFromExcel.class)
    public void validateNoTravellerSelectedGivesError(String country,String startDate,String endDate,String errorMsg){
        CommonUtils.sureWait(3);
        tp.putCountryNameInSearchBox(country);
        tp.clickOnStartDate();
        tp.pickStartDateAndEndDate(startDate,endDate);
        tp.clickCutButton();
        tp.clickOnViewPlansButton();
        String actual=tp.getErrorMessage();
        String expected=errorMsg;
        Assert.assertEquals(actual,expected,"Did not throw error");
        ScreenShotUtil.takeScreenShot(driver,"validateNoTravellerSelectedGivesError");
    }
    @Test(priority = 9,dataProvider = "excelTestData",dataProviderClass = ReadAndWriteFromExcel.class)
    public void validateInvalidCountryNameShowsNoResult(String country){
        CommonUtils.sureWait(3);
        boolean res=tp.putCountryNameInSearchBox(country);
        Assert.assertFalse(res,"Did not throw error");
        ScreenShotUtil.takeScreenShot(driver,"validateInvalidCountryNameShowsNoResult");
    }
    @Test(priority = 10,dataProvider = "excelTestData",dataProviderClass = ReadAndWriteFromExcel.class)
    public void validateNotSelectingDateThrowsError(String country,String age1,String age2,String errorMsg){
        CommonUtils.sureWait(3);
        tp.putCountryNameInSearchBox(country);
        tp.clickOnAddTraveller();
        tp.clickOnNoOfTraveller();
        tp.selectAgeOfFirstStudent(age1);
        tp.selectAgeOfSecondStudent(age2);
        tp.clickOnNoButton();
        tp.clickOnSubmitButton();
        tp.clickCutButton();
        tp.clickOnViewPlansButton();
        String actual=tp.getErrorMessage();
        String expected=errorMsg;
        Assert.assertEquals(actual,expected,"Did not throw error");
        ScreenShotUtil.takeScreenShot(driver,"validateNotSelectingDateThrowsError");
    }
    @AfterMethod
    public void end(){
        CommonUtils.sureWait(3);
        driver.quit();
    }

}
