package com.cts.policy_bazaar.stepdefinitions;

import com.cts.policy_bazaar.browserutils.BrowserFactory;
import com.cts.policy_bazaar.frameworkutils.CommonUtils;
import com.cts.policy_bazaar.frameworkutils.PropertiesFileReader;
import com.cts.policy_bazaar.pageobjects.HealthInsurancePage;
import com.cts.policy_bazaar.pageobjects.HomePage;
import com.cts.policy_bazaar.pageobjects.PlansPage;
import com.cts.policy_bazaar.pageobjects.TravelInsurancePage;
import com.cts.policy_bazaar.seleniumutils.ScreenShotUtil;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Hooks {

    public static WebDriver driver;
    public static HomePage hp;
    public static TravelInsurancePage tp;
    public static PlansPage pp;
    public static HealthInsurancePage hi;

    @Before
    public void init() throws Exception {
        String bn = PropertiesFileReader.getPropertyValue("config", "browsername");
        String wr = PropertiesFileReader.getPropertyValue("config", "wheretorun");
        String remoteip = PropertiesFileReader.getPropertyValue("config", "hubip");
        String url = PropertiesFileReader.getPropertyValue("config", "url");

        driver = BrowserFactory.getBrowser(bn, wr, remoteip);
        BrowserFactory.OpenUrl(url);

        hp = new HomePage(driver);
        tp = new TravelInsurancePage(driver);
        pp = new PlansPage(driver);
        hi = new HealthInsurancePage(driver);
    }

    @After
    public void end(Scenario scenario) {
        try {
            System.out.println("AfterStep running for: " + scenario.getName());
            if (scenario.isFailed()) {
                System.out.println("Step failed, taking screenshot...");
                ScreenShotUtil.takeScreenShot(driver, scenario.getName().replaceAll(" ", "_"));
            }
        } catch (Exception e) {
            System.out.println("Failed to take screenshot: " + e.getMessage());
        } finally {
            CommonUtils.sureWait(3);
            if (driver != null) {
                driver.quit();
            }
        }
    }
}