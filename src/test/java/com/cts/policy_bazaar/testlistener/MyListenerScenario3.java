package com.cts.policy_bazaar.testlistener;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.cts.policy_bazaar.seleniumutils.ScreenShotUtil;
import com.cts.policy_bazaar.testrunner.Scenario1_Runner;
import com.cts.policy_bazaar.testrunner.Scenario3_Runner;
import org.openqa.selenium.WebDriver;
import org.testng.*;

public class MyListenerScenario3 implements ITestListener {

    private ExtentReports extent;
    private ExtentTest test;

    @Override
    public void onStart(ITestContext context) {
        String reportPath = "test-output/ThirdPartyReports/ExtentReportScenario3.html";

        ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
        spark.config().setDocumentTitle("Automation Report");
        spark.config().setReportName("Policy Bazaar Suite");
        spark.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(spark);
        extent.setSystemInfo("Tester", "CodeBreakers");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Browser", "Chrome");
    }

    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
        test.log(Status.INFO, "Test Started: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS, "Test Passed: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = Scenario3_Runner.driver;
        String path = ScreenShotUtil.takeScreenShot(driver, result.getName());
        test.log(Status.FAIL, "Test Failed: " + result.getMethod().getMethodName());
        test.addScreenCaptureFromPath(path);
        System.out.println("Screenshot saved at: " + path);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.log(Status.SKIP, "Test Skipped: " + result.getMethod().getMethodName());
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}
