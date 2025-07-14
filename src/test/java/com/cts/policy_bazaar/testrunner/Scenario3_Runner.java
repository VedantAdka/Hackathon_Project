package com.cts.policy_bazaar.testrunner;

import com.cts.policy_bazaar.browserutils.BrowserFactory;
import com.cts.policy_bazaar.frameworkutils.CommonUtils;
import com.cts.policy_bazaar.frameworkutils.PropertiesFileReader;
import com.cts.policy_bazaar.frameworkutils.ReadAndWriteFromExcel;
import com.cts.policy_bazaar.pageobjects.HealthInsurancePage;
import com.cts.policy_bazaar.pageobjects.HomePage;
import com.cts.policy_bazaar.seleniumutils.ScreenShotUtil;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class Scenario3_Runner {
    public static WebDriver driver;
    HomePage hp=null;
    HealthInsurancePage hi=null;
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
            hi=new HealthInsurancePage(driver);
        } catch (Exception e) {
            ScreenShotUtil.takeScreenShot(driver);
            e.printStackTrace();
        }
    }

    @Test(priority = 0)
    public void validateIfInsuranceProductIsEnabled(){
        hp.hoverToInsuranceProducts();
        Assert.assertTrue(hp.insuranceProductsIsEnabled(),"It is not enabled");
    }
    @Test(priority = 1)
    public void validateIfHealthInsuranceIsEnabled(){
        hp.hoverToInsuranceProducts();
        Assert.assertTrue(hp.healthInsuranceIsEnabled(),"It is not enabled");
    }
    @Test(priority = 2, dataProvider = "excelTestData",dataProviderClass = ReadAndWriteFromExcel.class)
    public void validateIfWeSwitchedToHealthInsurancePage(String pageTitle){
        hp.hoverToInsuranceProducts();
        hp.selectHealthInsurance();
        String actual=hi.getTitle();
        String expected=pageTitle;
        Assert.assertEquals(actual,expected,"Did not switch to Health Insurance Page");
    }
    @Test(priority = 3)
    public void validateRetrievingHealthInssuranceData(){
        hp.hoverToInsuranceProducts();
        hp.selectHealthInsurance();
        hi.clickOnCloseButton();
        hi.clickOnViewMorePlansButton();
        hi.clickOnListOfViewMorePlansButtons();
        List<String> insuranceName=hi.getInsuranceNames();
        List<String> coverAmount=hi.getCoverAmount();
        List<String> startAmount=hi.getStartAtAmount();
        ReadAndWriteFromExcel.writeDataForScenario3(insuranceName,"Insurance Name",0);
        ReadAndWriteFromExcel.writeDataForScenario3(coverAmount,"Cover Amount",1);
        ReadAndWriteFromExcel.writeDataForScenario3(startAmount,"Start Amount",2);
        int actual=insuranceName.size();
        Assert.assertTrue(actual!=0,"Did not get any Insurance Plans");
    }
    @AfterMethod
    public void end(){
        CommonUtils.sureWait(2);
        driver.quit();
    }

}
