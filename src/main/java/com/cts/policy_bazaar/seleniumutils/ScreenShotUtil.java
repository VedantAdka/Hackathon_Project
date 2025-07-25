package com.cts.policy_bazaar.seleniumutils;

import com.cts.policy_bazaar.frameworkutils.CommonUtils;
import org.openqa.selenium.*;

import java.io.File;

public class ScreenShotUtil {

    // Takes a screenshot of the current browser window and saves it with a timestamp
    public static void takeScreenShot(WebDriver driver) {
        TakesScreenshot tss = (TakesScreenshot) driver;
        File src = tss.getScreenshotAs(OutputType.FILE);
        File dest = new File("screenshot/" + CommonUtils.getCurrentDateTime() + ".png");
        src.renameTo(dest);
    }

    // Takes a screenshot and saves it with the test name and timestamp
    public static String takeScreenShot(WebDriver driver, String testName) {
        TakesScreenshot tss = (TakesScreenshot) driver;
        File src = tss.getScreenshotAs(OutputType.FILE);
        File dest = new File("screenshot/" + testName + "_" + CommonUtils.getCurrentDateTime() + ".png");
        src.renameTo(dest);
        return testName;
    }

    // Takes a screenshot of a specific WebElement and saves it with a timestamp
    public static void takeScreenShot(WebElement ele) {
        TakesScreenshot tss = (TakesScreenshot) ele;
        File src = tss.getScreenshotAs(OutputType.FILE);
        File dest = new File("screenshot/" + CommonUtils.getCurrentDateTime() + ".png");
        src.renameTo(dest);
    }
}
