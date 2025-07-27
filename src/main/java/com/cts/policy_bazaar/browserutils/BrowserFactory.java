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

    // Opens the specified URL in the current browser instance
    public static void OpenUrl(String url) {
        driver.get(url);
    }

    // Returns a WebDriver instance based on browser name and run type (cloud/local)
    public static WebDriver getBrowser(String bn, String wr, String hubip) throws Exception {
        if (wr.toLowerCase().intern().equals("cloud")) {
            driver = runRemote(bn, hubip);
        } else {
            driver = runLocal(bn);
        }
        return driver;
    }

    // Launches browser locally with required options
    private static WebDriver runLocal(String bname) {
        // Disable notifications and automation flags
        ChromeOptions chromeOptions = new ChromeOptions();
        EdgeOptions edgeOptions=new EdgeOptions();
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("--disable-blink-features=AutomationControlled");
        chromeOptions.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
        edgeOptions.addArguments("--disable-notifications");
        edgeOptions.addArguments("--disable-blink-features=AutomationControlled");
        edgeOptions.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
        // Choose browser for remote execution
        switch (bname.intern().toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver(chromeOptions);
                break;
            case "edge":
                driver = new EdgeDriver(edgeOptions);
                break;
            default:
                chromeOptions.addArguments("--headless=new");
                driver = new ChromeDriver(chromeOptions);
                break;
        }
        driver.manage().window().maximize();
        Waits.implicitlyWait(driver,30);
        return driver;
    }

    // Launches browser on remote server/grid
    private static WebDriver runRemote(String bn, String ip) throws Exception {
        // Disable notifications and automation flags
        ChromeOptions chromeOptions = new ChromeOptions();
        EdgeOptions edgeOptions=new EdgeOptions();
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("--disable-blink-features=AutomationControlled");
        chromeOptions.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
        edgeOptions.addArguments("--disable-notifications");
        edgeOptions.addArguments("--disable-blink-features=AutomationControlled");
        edgeOptions.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
        DesiredCapabilities dc = new DesiredCapabilities();
        // Choose browser for remote execution
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
                chromeOptions.addArguments("--headless=new");
                driver = new ChromeDriver(chromeOptions);
                break;
        }
        driver.manage().window().maximize();
        Waits.implicitlyWait(driver,30);
        return driver;
    }

    // Returns browser instance and opens the given URL
    public static WebDriver getBrowser(String bname, String url) {
        // Disable notifications and automation flags
        ChromeOptions chromeOptions = new ChromeOptions();
        EdgeOptions edgeOptions=new EdgeOptions();
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("--disable-blink-features=AutomationControlled");
        chromeOptions.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
        edgeOptions.addArguments("--disable-notifications");
        edgeOptions.addArguments("--disable-blink-features=AutomationControlled");
        edgeOptions.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
        // Choose browser based on input
        switch (bname.intern().toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver(chromeOptions);
                break;
            case "edge":
                driver = new EdgeDriver(edgeOptions);
                break;
            default:
                chromeOptions.addArguments("--headless=new");
                driver = new ChromeDriver(chromeOptions);
                break;
        }
        driver.manage().window().maximize();
        Waits.implicitlyWait(driver,30);
        driver.get(url);
        return driver;
    }

    // Returns browser instance without opening any URL
    public static WebDriver getBrowser(String bname) {
        // Disable notifications and automation flags
        ChromeOptions chromeOptions = new ChromeOptions();
        EdgeOptions edgeOptions=new EdgeOptions();
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("--incognito");
        chromeOptions.addArguments("--disable-blink-features=AutomationControlled");
        chromeOptions.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
        edgeOptions.addArguments("--disable-notifications");
        edgeOptions.addArguments("--incognito");
        edgeOptions.addArguments("--disable-blink-features=AutomationControlled");
        edgeOptions.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
        // Choose browser based on input
        switch (bname.intern().toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver(chromeOptions);
                break;
            case "edge":
                driver = new EdgeDriver(edgeOptions);
                break;
            default:
                chromeOptions.addArguments("--headless=new");
                driver = new ChromeDriver(chromeOptions);
                break;
        }
        driver.manage().window().maximize();
        Waits.implicitlyWait(driver,30); 
        return driver;
    }
}
