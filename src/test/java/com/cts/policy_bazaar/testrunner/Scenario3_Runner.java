package com.cts.policy_bazaar.testrunner;

import com.cts.policy_bazaar.browserutils.BrowserFactory;
import com.cts.policy_bazaar.frameworkutils.CommonUtils;
import com.cts.policy_bazaar.frameworkutils.PropertiesFileReader;
import com.cts.policy_bazaar.frameworkutils.ReadAndWriteFromExcel;
import com.cts.policy_bazaar.pageobjects.HealthInsurancePage;
import com.cts.policy_bazaar.pageobjects.HomePage;
import com.cts.policy_bazaar.seleniumutils.ScreenShotUtil;
import com.cts.policy_bazaar.testlistener.MyListener;
import com.cts.policy_bazaar.testlistener.MyListenerScenario3;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;
@Listeners(MyListenerScenario3.class)
public class Scenario3_Runner {

    public static WebDriver driver;
    HomePage hp = null;
    HealthInsurancePage hi = null;
    String bn, wr, url, remoteip;

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
            hi = new HealthInsurancePage(driver);

        } catch (Exception e) {
            ScreenShotUtil.takeScreenShot(driver);
            e.printStackTrace();
        }
    }

    @Test(priority = 0, dataProvider = "excelTestData", dataProviderClass = ReadAndWriteFromExcel.class)
    public void validateIfInsuranceProductIsEnabled(String dummy, String rowNum) {
        try {
            hp.hoverToInsuranceProducts();
            Assert.assertTrue(hp.insuranceProductsIsEnabled(), "Insurance Products not enabled");
            ReadAndWriteFromExcel.writeResult("PASS", Integer.parseInt(rowNum));
        } catch (Exception | AssertionError e) {
            ReadAndWriteFromExcel.writeResult("FAIL", Integer.parseInt(rowNum));
            ScreenShotUtil.takeScreenShot(driver, "validateIfInsuranceProductIsEnabled");
            Assert.fail(e.getMessage());
        }
    }

    @Test(priority = 1, dataProvider = "excelTestData", dataProviderClass = ReadAndWriteFromExcel.class)
    public void validateIfHealthInsuranceIsEnabled(String dummy, String rowNum) {
        try {
            hp.hoverToInsuranceProducts();
            Assert.assertTrue(hp.healthInsuranceIsEnabled(), "Health Insurance not enabled");
            ReadAndWriteFromExcel.writeResult("PASS", Integer.parseInt(rowNum));
        } catch (Exception | AssertionError e) {
            ReadAndWriteFromExcel.writeResult("FAIL", Integer.parseInt(rowNum));
            ScreenShotUtil.takeScreenShot(driver, "validateIfHealthInsuranceIsEnabled");
            Assert.fail(e.getMessage());
        }
    }

    @Test(priority = 2, dataProvider = "excelTestData", dataProviderClass = ReadAndWriteFromExcel.class)
    public void validateIfWeSwitchedToHealthInsurancePage(String pageTitle, String rowNum) {
        try {
            hp.hoverToInsuranceProducts();
            hp.selectHealthInsurance();
            String actualTitle = hi.getTitle();
            Assert.assertEquals(actualTitle, pageTitle, "Did not switch to Health Insurance Page");
            ReadAndWriteFromExcel.writeResult("PASS", Integer.parseInt(rowNum));
        } catch (Exception e) {
            ReadAndWriteFromExcel.writeResult("FAIL", Integer.parseInt(rowNum));
            ScreenShotUtil.takeScreenShot(driver);
            throw e;
        }
    }

    @Test(priority = 3, dataProvider = "excelTestData", dataProviderClass = ReadAndWriteFromExcel.class)
    public void validateRetrievingHealthInsuranceData(String dummy, String rowNum) {
        try {
            hp.hoverToInsuranceProducts();
            hp.selectHealthInsurance();

            hi.clickOnCloseButton();
            hi.clickOnViewMorePlansButton();
            hi.clickOnListOfViewMorePlansButtons();

            List<String> insuranceName = hi.getInsuranceNames();
            List<String> coverAmount = hi.getCoverAmount();
            List<String> startAmount = hi.getStartAtAmount();

            ReadAndWriteFromExcel.writeDataForScenarios(insuranceName, "Insurance Name", 0,"testdata/Scenario3_Output.xlsx");
            ReadAndWriteFromExcel.writeDataForScenarios(coverAmount, "Cover Amount", 1,"testdata/Scenario3_Output.xlsx");
            ReadAndWriteFromExcel.writeDataForScenarios(startAmount, "Start Amount", 2,"testdata/Scenario3_Output.xlsx");

            Assert.assertTrue(insuranceName.size() > 0, "No insurance plans retrieved");

            ReadAndWriteFromExcel.writeResult("PASS", Integer.parseInt(rowNum));
        } catch (Exception | AssertionError e) {
            ReadAndWriteFromExcel.writeResult("FAIL", Integer.parseInt(rowNum));
            ScreenShotUtil.takeScreenShot(driver, "validateRetrievingHealthInsuranceData");
            Assert.fail(e.getMessage());
        }
    }

    @AfterMethod
    public void end() {
        CommonUtils.sureWait(2);
        driver.quit();
    }
}
