package com.cts.policy_bazaar.testrunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.cts.policy_bazaar.stepdefinitions", "com.cts.policy_bazaar.frameworkutils"},
        tags = "@Regression",
        // Reporting plugins for different formats
        plugin = {
                "pretty",
                "html:target/regression_cucumber_reports/regression_cucumber.html",
                "json:target/regression_cucumber_reports/regression_cucumber.json",
                "junit:target/regression_cucumber_reports/regression_cucumber.xml",
        },
        // Removes unnecessary characters from console output
        monochrome = true
)

public class RegressionBDDTestRunner extends AbstractTestNGCucumberTests {
}
