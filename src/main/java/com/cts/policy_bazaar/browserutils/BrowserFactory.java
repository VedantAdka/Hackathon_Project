package com.cts.policy_bazaar.browserutils;



import com.cts.policy_bazaar.seleniumutils.Waits;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class BrowserFactory {
    private static WebDriver driver;

    public static void OpenUrl(String url) {
        driver.get(url);
    }

    public static WebDriver getBrowser(String bn, String wr, String hubip) throws Exception {
        if (wr.toLowerCase().intern().equals("cloud")) {
            driver = runRemote(bn, hubip);
        } else {
            driver = runLocal(bn);
        }
        return driver;
    }

    private static WebDriver runLocal(String bname) {
        ChromeOptions chromeOptions = new ChromeOptions();
        EdgeOptions edgeOptions=new EdgeOptions();
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("--disable-blink-features=AutomationControlled");
        chromeOptions.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
        edgeOptions.addArguments("--disable-notifications");
        edgeOptions.addArguments("--disable-blink-features=AutomationControlled");
        edgeOptions.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
        switch (bname.intern().toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver(chromeOptions);
                break;
            case "edge":
                driver = new EdgeDriver(edgeOptions);
                break;
            default:
                ChromeOptions co = new ChromeOptions();
                co.addArguments("--headless=new");
                driver = new ChromeDriver(co);
                break;
        }
        driver.manage().window().maximize();
        Waits.implicitlyWait(driver,30);
        return driver;
    }

    private static WebDriver runRemote(String bn, String ip) throws Exception {
        DesiredCapabilities dc = new DesiredCapabilities();
        switch (bn.intern().toLowerCase()) {
            case "chrome":
                dc.setBrowserName("chrome");
//                ChromeOptions co = new ChromeOptions();
//                co.merge(dc);
                driver = new RemoteWebDriver(new URL(ip + "/wd/hub"), dc);
                break;
            case "edge":
                dc.setBrowserName("edge");
                driver = new RemoteWebDriver(new URL(ip + "/wd/hub"), dc);
                break;
            default:
                ChromeOptions co = new ChromeOptions();
                co.addArguments("--headless=new");
                driver = new ChromeDriver(co);
                break;
        }
        driver.manage().window().maximize();
        Waits.implicitlyWait(driver,30);
        return driver;
    }

    public static WebDriver getBrowser(String bname, String url) {
        ChromeOptions chromeOptions = new ChromeOptions();
        EdgeOptions edgeOptions=new EdgeOptions();
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("--disable-blink-features=AutomationControlled");
        chromeOptions.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
        edgeOptions.addArguments("--disable-notifications");
        edgeOptions.addArguments("--disable-blink-features=AutomationControlled");
        edgeOptions.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
        switch (bname.intern().toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver(chromeOptions);
                break;
            case "edge":
                driver = new EdgeDriver(edgeOptions);
                break;
            default:
                ChromeOptions co = new ChromeOptions();
                co.addArguments("--headless=new");
                driver = new ChromeDriver(co);
                break;
        }
        driver.manage().window().maximize();
        Waits.implicitlyWait(driver,30);
        driver.get(url);
        return driver;
    }

    public static WebDriver getBrowser(String bname) {
        ChromeOptions chromeOptions = new ChromeOptions();
        EdgeOptions edgeOptions=new EdgeOptions();
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("--disable-blink-features=AutomationControlled");
        chromeOptions.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
        edgeOptions.addArguments("--disable-notifications");
        edgeOptions.addArguments("--disable-blink-features=AutomationControlled");
        edgeOptions.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
        switch (bname.intern().toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver(chromeOptions);
                break;
            case "edge":
                driver = new EdgeDriver(edgeOptions);
                break;
            default:
                ChromeOptions co = new ChromeOptions();
                co.addArguments("--headless=new");
                driver = new ChromeDriver(co);
                break;
        }
        driver.manage().window().maximize();
        Waits.implicitlyWait(driver,30); 
        return driver;
    }
}
