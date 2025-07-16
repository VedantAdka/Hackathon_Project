package com.cts.policy_bazaar.stepdefinitions;

import com.cts.policy_bazaar.browserutils.BrowserFactory;
import com.cts.policy_bazaar.frameworkutils.CommonUtils;
import com.cts.policy_bazaar.frameworkutils.PropertiesFileReader;
import com.cts.policy_bazaar.pageobjects.HomePage;
import com.cts.policy_bazaar.pageobjects.PlansPage;
import com.cts.policy_bazaar.pageobjects.TravelInsurancePage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;

import java.io.FileNotFoundException;

public class Hooks {
    public static WebDriver driver;
    public static HomePage hp;
    public static TravelInsurancePage tp;
    public static PlansPage pp;

    @Before
    public void init() throws Exception {
        String bn = PropertiesFileReader.getPropertyValue("config", "browsername");
        String wr = PropertiesFileReader.getPropertyValue("config", "wheretorun");
        String remoteip = PropertiesFileReader.getPropertyValue("config", "hubip");
        String url = PropertiesFileReader.getPropertyValue("config", "url");
        driver = BrowserFactory.getBrowser(bn, wr, remoteip);
        BrowserFactory.OpenUrl(url);
        hp=new HomePage(driver);
        tp=new TravelInsurancePage(driver);
        pp=new PlansPage(driver);
    }
    @After
    public void end(){
        CommonUtils.sureWait(3);
        if(driver!=null){
            driver.quit();
        }
    }
}
