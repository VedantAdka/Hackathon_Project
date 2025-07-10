package com.cts.policy_bazaar.seleniumutils;

import com.cts.policy_bazaar.frameworkutils.CommonUtils;
import org.openqa.selenium.*;

import java.io.File;

public class ScreenShotUtil {
    public static void takeScreenShot(WebDriver driver) {
        TakesScreenshot tss = (TakesScreenshot) driver;
        File src = tss.getScreenshotAs(OutputType.FILE);
        File dest = new File("screenshot/" + CommonUtils.getCurrentDateTime() + ".png");
        src.renameTo(dest);
    }

    public static String takeScreenShot(WebDriver driver, String testName) {
        TakesScreenshot tss = (TakesScreenshot) driver;
        File src = tss.getScreenshotAs(OutputType.FILE);
        File dest = new File("screenshot/" + testName + "_" + CommonUtils.getCurrentDateTime() + ".png");
        src.renameTo(dest);
        return testName;
    }

    public static void takeScreenShot(WebElement ele) {
        TakesScreenshot tss = (TakesScreenshot) ele;
        File src = tss.getScreenshotAs(OutputType.FILE);
        File dest = new File("screenshot/" + CommonUtils.getCurrentDateTime() + ".png");
        src.renameTo(dest);
    }
}
