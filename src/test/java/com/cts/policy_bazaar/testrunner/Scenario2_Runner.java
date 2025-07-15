package com.cts.policy_bazaar.testrunner;

import com.cts.policy_bazaar.browserutils.BrowserFactory;
import com.cts.policy_bazaar.frameworkutils.PropertiesFileReader;
import com.cts.policy_bazaar.frameworkutils.ReadAndWriteFromExcel;
import com.cts.policy_bazaar.pageobjects.*;
import com.cts.policy_bazaar.testlistener.MyListener;
import com.cts.policy_bazaar.seleniumutils.ScreenShotUtil;
import com.cts.policy_bazaar.testlistener.MyListenerScenario2;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
@Listeners(MyListenerScenario2.class)
public class Scenario2_Runner {

    public static WebDriver driver;
    HomePage homePage;
    CarInsuranceLandingPage landingPage;
    CityAndBrandSelection selectionPage;
    CarModelPage modelPage;
    FormPage formPage;

    @BeforeMethod
    public void setup() throws Exception {
        String browser = PropertiesFileReader.getPropertyValue("config", "browsername");
        String url = PropertiesFileReader.getPropertyValue("config", "url");
        String remoteIP = PropertiesFileReader.getPropertyValue("config", "hubip");
        String wr = PropertiesFileReader.getPropertyValue("config", "wheretorun");

        driver = BrowserFactory.getBrowser(browser, wr, remoteIP);
        BrowserFactory.OpenUrl(url);

        homePage = new HomePage(driver);
        landingPage = new CarInsuranceLandingPage(driver);
        selectionPage = new CityAndBrandSelection(driver);
        modelPage = new CarModelPage(driver);
        formPage = new FormPage(driver);
    }

    @Test(priority = 1, dataProvider = "excelTestData", dataProviderClass = ReadAndWriteFromExcel.class)
    public void accessCarInsurancePage(String city, String brand, String model, String fuel, String variant, String name, String phone, String rowIndex) {
        try {
            homePage.clickCarSection();
            Assert.assertTrue(landingPage.isCarRegTextBoxDisplayed());
            ReadAndWriteFromExcel.writeResult("PASS", Integer.parseInt(rowIndex));
        } catch (Exception e) {
            ReadAndWriteFromExcel.writeResult("FAIL", Integer.parseInt(rowIndex));
            Assert.fail(e.getMessage());
        }
    }

    @Test(priority = 2, dataProvider = "excelTestData", dataProviderClass = ReadAndWriteFromExcel.class)
    public void clickWithoutCarNumber(String city, String brand, String model, String fuel, String variant, String name, String phone, String rowIndex) {
        try {
            homePage.clickCarSection();
            landingPage.clickClickHereWithoutCarNumber();
            Assert.assertTrue(selectionPage.isCityTextVisible());
            ReadAndWriteFromExcel.writeResult("PASS", Integer.parseInt(rowIndex));
        } catch (Exception e) {
            ReadAndWriteFromExcel.writeResult("FAIL", Integer.parseInt(rowIndex));
            Assert.fail(e.getMessage());
        }
    }

    @Test(priority = 3, dataProvider = "excelTestData", dataProviderClass = ReadAndWriteFromExcel.class)
    public void selectCity(String city, String brand, String model, String fuel, String variant, String name, String phone, String rowIndex) {
        try {
            homePage.clickCarSection();
            landingPage.clickClickHereWithoutCarNumber();
            selectionPage.selectCity(city);
            Assert.assertTrue(selectionPage.isCarBrandSectionDisplayed());
            ReadAndWriteFromExcel.writeResult("PASS", Integer.parseInt(rowIndex));
        } catch (Exception e) {
            ReadAndWriteFromExcel.writeResult("FAIL", Integer.parseInt(rowIndex));
            Assert.fail(e.getMessage());
        }
    }

    @Test(priority = 4, dataProvider = "excelTestData", dataProviderClass = ReadAndWriteFromExcel.class)
    public void selectBrand(String city, String brand, String model, String fuel, String variant, String name, String phone, String rowIndex) {
        try {
            homePage.clickCarSection();
            landingPage.clickClickHereWithoutCarNumber();
            selectionPage.selectCity(city);
            selectionPage.selectBrand(brand);
            Assert.assertTrue(modelPage.isSearchModelInputDisplayed());
            ReadAndWriteFromExcel.writeResult("PASS", Integer.parseInt(rowIndex));
        } catch (Exception e) {
            ReadAndWriteFromExcel.writeResult("FAIL", Integer.parseInt(rowIndex));
            Assert.fail(e.getMessage());
        }
    }

    @Test(priority = 5, dataProvider = "excelTestData", dataProviderClass = ReadAndWriteFromExcel.class)
    public void selectCarModel(String city, String brand, String model, String fuel, String variant, String name, String phone, String rowIndex) {
        try {
            homePage.clickCarSection();
            landingPage.clickClickHereWithoutCarNumber();
            selectionPage.selectCity(city);
            selectionPage.selectBrand(brand);
            modelPage.searchModel(model);
            Assert.assertTrue(modelPage.isFuelTypeHeaderDisplayed());
            ReadAndWriteFromExcel.writeResult("PASS", Integer.parseInt(rowIndex));
        } catch (Exception e) {
            ReadAndWriteFromExcel.writeResult("FAIL", Integer.parseInt(rowIndex));
            Assert.fail(e.getMessage());
        }
    }

    @Test(priority = 6, dataProvider = "excelTestData", dataProviderClass = ReadAndWriteFromExcel.class)
    public void selectFuelType(String city, String brand, String model, String fuel, String variant, String name, String phone, String rowIndex) {
        try {
            homePage.clickCarSection();
            landingPage.clickClickHereWithoutCarNumber();
            selectionPage.selectCity(city);
            selectionPage.selectBrand(brand);
            modelPage.searchModel(model);
            modelPage.selectFuel(fuel);
            Assert.assertTrue(modelPage.isVariantHeaderDisplayed());
            ReadAndWriteFromExcel.writeResult("PASS", Integer.parseInt(rowIndex));
        } catch (Exception e) {
            ReadAndWriteFromExcel.writeResult("FAIL", Integer.parseInt(rowIndex));
            Assert.fail(e.getMessage());
        }
    }

    @Test(priority = 7, dataProvider = "excelTestData", dataProviderClass = ReadAndWriteFromExcel.class)
    public void selectVariant(String city, String brand, String model, String fuel, String variant, String name, String phone, String rowIndex) {
        try {
            homePage.clickCarSection();
            landingPage.clickClickHereWithoutCarNumber();
            selectionPage.selectCity(city);
            selectionPage.selectBrand(brand);
            modelPage.searchModel(model);
            modelPage.selectFuel(fuel);
            modelPage.selectVariant(variant);
            Assert.assertTrue(formPage.isFormPageDisplayed());
            ReadAndWriteFromExcel.writeResult("PASS", Integer.parseInt(rowIndex));
        } catch (Exception e) {
            ReadAndWriteFromExcel.writeResult("FAIL", Integer.parseInt(rowIndex));
            Assert.fail(e.getMessage());
        }
    }

    @Test(priority = 8, dataProvider = "excelTestData", dataProviderClass = ReadAndWriteFromExcel.class)
    public void invalidPhoneCheck(String city, String brand, String model, String fuel, String variant, String name, String phone, String rowIndex) {
        try {
            homePage.clickCarSection();
            landingPage.clickClickHereWithoutCarNumber();
            selectionPage.selectCity(city);
            selectionPage.selectBrand(brand);
            modelPage.searchModel(model);
            modelPage.selectFuel(fuel);
            modelPage.selectVariant(variant);
            formPage.enterDetails(name, phone);
            boolean isEnabled = formPage.isSubmitEnabled();
            if (!isEnabled) {
                ScreenShotUtil.takeScreenShot(driver);
            }
            Assert.assertFalse(isEnabled, "Submit should be disabled for invalid phone.");
            ReadAndWriteFromExcel.writeResult("PASS", Integer.parseInt(rowIndex));
        } catch (Exception e) {
            ReadAndWriteFromExcel.writeResult("FAIL", Integer.parseInt(rowIndex));
            Assert.fail(e.getMessage());
        }
    }

    @AfterMethod
    public void terminate() {
        driver.quit();
    }
}

