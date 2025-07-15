package com.cts.policy_bazaar.testrunner;

import com.cts.policy_bazaar.browserutils.BrowserFactory;
import com.cts.policy_bazaar.frameworkutils.CommonUtils;
import com.cts.policy_bazaar.frameworkutils.PropertiesFileReader;
import com.cts.policy_bazaar.frameworkutils.ReadAndWriteFromExcel;
import com.cts.policy_bazaar.pageobjects.HomePage;
import com.cts.policy_bazaar.pageobjects.PlansPage;
import com.cts.policy_bazaar.pageobjects.TravelInsurancePage;
import com.cts.policy_bazaar.testlistener.MyListener;
import com.cts.policy_bazaar.seleniumutils.ScreenShotUtil;
import com.cts.policy_bazaar.testlistener.MyListenerCombined;
import com.cts.policy_bazaar.testlistener.MyListenerScenario1;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

@Listeners(MyListenerCombined.class)
public class Scenario1_Runner {
    public static WebDriver driver;
    HomePage hp = null;
    TravelInsurancePage tp = null;
    PlansPage pp = null;
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
            hp = new HomePage(driver);
            tp = new TravelInsurancePage(driver);
            pp = new PlansPage(driver);
            hp.clickOnTravelInsurance();
        } catch (Exception e) {
            ScreenShotUtil.takeScreenShot(driver);
            e.printStackTrace();
        }
    }

    @Test(priority = 0, dataProvider = "excelTestData", dataProviderClass = ReadAndWriteFromExcel.class)
    public void validateAccessingTravelInsurancePage(String rowNumStr) {
        try {
            String actual = driver.getTitle();
            String expected = hp.getTitle();
            Assert.assertEquals(actual, expected, "Title does not match");
            ReadAndWriteFromExcel.writeResult("PASS", Integer.parseInt(rowNumStr));
        } catch (Exception | AssertionError e) {
            ReadAndWriteFromExcel.writeResult("FAIL", Integer.parseInt(rowNumStr));
            ScreenShotUtil.takeScreenShot(driver, "validateAccessingTravelInsurancePage");
            Assert.fail(e.getMessage());
        }
    }

    @Test(priority = 1, dataProvider = "excelTestData", dataProviderClass = ReadAndWriteFromExcel.class)
    public void validateSelectingDestination(String country, String rowNumStr) {
        try {
            CommonUtils.sureWait(3);
            tp.putCountryNameInSearchBox(country);
            String actual = tp.getCountryNameSelectedInSearchBox();
            Assert.assertEquals(actual, country, "Wrong country selected for destination");
            ReadAndWriteFromExcel.writeResult("PASS", Integer.parseInt(rowNumStr));
        } catch (Exception | AssertionError e) {
            ReadAndWriteFromExcel.writeResult("FAIL", Integer.parseInt(rowNumStr));
            ScreenShotUtil.takeScreenShot(driver, "validateSelectingDestination");
            Assert.fail(e.getMessage());
        }
    }

    @Test(priority = 2, dataProvider = "excelTestData", dataProviderClass = ReadAndWriteFromExcel.class)
    public void validateTravelStartAndEndDate(String startDate, String endDate, String rowNumStr) {
        try {
            tp.clickOnStartDate();
            tp.pickStartDateAndEndDate(startDate, endDate);
            Assert.assertTrue(tp.getSelectedStartAndEndDate()[0].contains(startDate), "Wrong Dates selected");
            ReadAndWriteFromExcel.writeResult("PASS", Integer.parseInt(rowNumStr));
        } catch (Exception | AssertionError e) {
            ReadAndWriteFromExcel.writeResult("FAIL", Integer.parseInt(rowNumStr));
            ScreenShotUtil.takeScreenShot(driver, "validateTravelStartAndEndDate");
            Assert.fail(e.getMessage());
        }
    }

    @Test(priority = 3, dataProvider = "excelTestData", dataProviderClass = ReadAndWriteFromExcel.class)
    public void validateSelecting2TravellersAndGoingToPlansPage(String age1, String age2, String message, String rowNumStr) {
        try {
            tp.clickOnAddTraveller();
            tp.clickOnNoOfTraveller();
            tp.selectAgeOfFirstStudent(age1);
            tp.selectAgeOfSecondStudent(age2);
            tp.clickOnNoButton();
            tp.clickOnSubmitButton();
            Assert.assertEquals(tp.getNoOfTravellerMsg(), message, "2 Travellers not selected");
            ReadAndWriteFromExcel.writeResult("PASS", Integer.parseInt(rowNumStr));
        } catch (Exception | AssertionError e) {
            ReadAndWriteFromExcel.writeResult("FAIL", Integer.parseInt(rowNumStr));
            ScreenShotUtil.takeScreenShot(driver, "validateSelecting2TravellersAndGoingToPlansPage");
            Assert.fail(e.getMessage());
        }
    }

    @Test(priority = 4, dataProvider = "excelTestData", dataProviderClass = ReadAndWriteFromExcel.class)
    public void validateClickingOnViewPlansAndGoingToPlansPage(String country, String startDate, String endDate, String age1, String age2, String rowNumStr) {
        try {
            CommonUtils.sureWait(3);
            tp.putCountryNameInSearchBox(country);
            tp.clickOnStartDate();
            tp.pickStartDateAndEndDate(startDate, endDate);
            tp.clickOnNoOfTraveller();
            tp.selectAgeOfFirstStudent(age1);
            tp.selectAgeOfSecondStudent(age2);
            tp.clickOnNoButton();
            tp.clickOnSubmitButton();
            tp.clickOnViewPlansButton();
            Assert.assertTrue(pp.plansPageDisplayed(), "Did not switch to Plans Page");
            ReadAndWriteFromExcel.writeResult("PASS", Integer.parseInt(rowNumStr));
        } catch (Exception | AssertionError e) {
            ReadAndWriteFromExcel.writeResult("FAIL", Integer.parseInt(rowNumStr));
            ScreenShotUtil.takeScreenShot(driver, "validateClickingOnViewPlansAndGoingToPlansPage");
            Assert.fail(e.getMessage());
        }
    }

    @Test(priority = 5, dataProvider = "excelTestData", dataProviderClass = ReadAndWriteFromExcel.class)
    public void validateSelectingStudentPlans(String country, String startDate, String endDate, String age1, String age2, String duration, String rowNumStr) {
        try {
            tp.putCountryNameInSearchBox(country);
            tp.clickOnStartDate();
            tp.pickStartDateAndEndDate(startDate, endDate);
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
            Assert.assertTrue(pp.studentsPlansDisplayed(), "Did not get selected");
            ReadAndWriteFromExcel.writeResult("PASS", Integer.parseInt(rowNumStr));
        } catch (Exception | AssertionError e) {
            ReadAndWriteFromExcel.writeResult("FAIL", Integer.parseInt(rowNumStr));
            ScreenShotUtil.takeScreenShot(driver, "validateSelectingStudentPlans");
            Assert.fail(e.getMessage());
        }
    }

    @Test(priority = 6, dataProvider = "excelTestData", dataProviderClass = ReadAndWriteFromExcel.class)
    public void validatedSortingPlansFromLowToHigh(String country, String startDate, String endDate, String age1, String age2, String duration, String rowNumStr) {
        try {
            tp.putCountryNameInSearchBox(country);
            tp.clickOnStartDate();
            tp.pickStartDateAndEndDate(startDate, endDate);
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
            Assert.assertTrue(pp.lowToHighBtnSelected(), "Did not get sorted");
            ReadAndWriteFromExcel.writeResult("PASS", Integer.parseInt(rowNumStr));
        } catch (Exception | AssertionError e) {
            ReadAndWriteFromExcel.writeResult("FAIL", Integer.parseInt(rowNumStr));
            ScreenShotUtil.takeScreenShot(driver, "validatedSortingPlansFromLowToHigh");
            Assert.fail(e.getMessage());
        }
    }

    @Test(priority = 7, dataProvider = "excelTestData", dataProviderClass = ReadAndWriteFromExcel.class)
    public void validateGettingTop3Plans(String country, String startDate, String endDate, String age1, String age2, String duration, String rowNumStr) {
        try {
            tp.putCountryNameInSearchBox(country);
            tp.clickOnStartDate();
            tp.pickStartDateAndEndDate(startDate, endDate);
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
            ReadAndWriteFromExcel.writeDataForScenarios(insuranceCompanyName,"Company Name",0,"testdata/Scenario1_Output.xlsx");
            ReadAndWriteFromExcel.writeDataForScenarios(insuranceAmount,"Insurance Amount",1,"testdata/Scenario1_Output.xlsx");
            int actual=insuranceAmount.size();
            Assert.assertTrue(actual!=0, "Did not get any plans");
            ReadAndWriteFromExcel.writeResult("PASS", Integer.parseInt(rowNumStr));
        } catch (Exception | AssertionError e) {
            ReadAndWriteFromExcel.writeResult("FAIL", Integer.parseInt(rowNumStr));
            ScreenShotUtil.takeScreenShot(driver, "validateGettingTop3Plans");
            Assert.fail(e.getMessage());
        }
    }

    @Test(priority = 8, dataProvider = "excelTestData", dataProviderClass = ReadAndWriteFromExcel.class)
    public void validateNoTravellerSelectedGivesError(String country, String startDate, String endDate, String errorMsg, String rowNumStr) {
        try {
            tp.putCountryNameInSearchBox(country);
            tp.clickOnStartDate();
            tp.pickStartDateAndEndDate(startDate, endDate);
            tp.clickCutButton();
            tp.clickOnViewPlansButton();
            Assert.assertEquals(tp.getErrorMessage(), errorMsg, "Did not throw error");
            ScreenShotUtil.takeScreenShot(driver,"validateNoTravellerSelectedGivesError");
            ReadAndWriteFromExcel.writeResult("PASS", Integer.parseInt(rowNumStr));
        } catch (Exception | AssertionError e) {
            ReadAndWriteFromExcel.writeResult("FAIL", Integer.parseInt(rowNumStr));
            ScreenShotUtil.takeScreenShot(driver, "validateNoTravellerSelectedGivesError");
            Assert.fail(e.getMessage());
        }
    }

    @Test(priority = 9, dataProvider = "excelTestData", dataProviderClass = ReadAndWriteFromExcel.class)
    public void validateInvalidCountryNameShowsNoResult(String country, String rowNumStr) {
        try {
            boolean res = tp.putCountryNameInSearchBox(country);
            Assert.assertFalse(res, "Did not throw error");
            ScreenShotUtil.takeScreenShot(driver,"validateInvalidCountryNameShowsNoResult");
            ReadAndWriteFromExcel.writeResult("PASS", Integer.parseInt(rowNumStr));
        } catch (Exception | AssertionError e) {
            ReadAndWriteFromExcel.writeResult("FAIL", Integer.parseInt(rowNumStr));
            ScreenShotUtil.takeScreenShot(driver, "validateInvalidCountryNameShowsNoResult");
            Assert.fail(e.getMessage());
        }
    }

    @Test(priority = 10, dataProvider = "excelTestData", dataProviderClass = ReadAndWriteFromExcel.class)
    public void validateNotSelectingDateThrowsError(String country, String age1, String age2, String errorMsg, String rowNumStr) {
        try {
            tp.putCountryNameInSearchBox(country);
            tp.clickOnAddTraveller();
            tp.clickOnNoOfTraveller();
            tp.selectAgeOfFirstStudent(age1);
            tp.selectAgeOfSecondStudent(age2);
            tp.clickOnNoButton();
            tp.clickOnSubmitButton();
            tp.clickCutButton();
            tp.clickOnViewPlansButton();
            Assert.assertEquals(tp.getErrorMessage(), errorMsg, "Did not throw error");
            ScreenShotUtil.takeScreenShot(driver,"validateNotSelectingDateThrowsError");
            ReadAndWriteFromExcel.writeResult("PASS", Integer.parseInt(rowNumStr));
        } catch (Exception | AssertionError e) {
            ReadAndWriteFromExcel.writeResult("FAIL", Integer.parseInt(rowNumStr));
            ScreenShotUtil.takeScreenShot(driver, "validateNotSelectingDateThrowsError");
            Assert.fail(e.getMessage());
        }
    }

    @AfterMethod
    public void end() {
        CommonUtils.sureWait(3);
        driver.quit();
    }
}




