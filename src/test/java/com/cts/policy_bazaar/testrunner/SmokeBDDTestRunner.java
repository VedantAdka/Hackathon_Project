package com.cts.policy_bazaar.testrunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.cts.policy_bazaar.stepdefinitions", "com.cts.policy_bazaar.frameworkutils"},
        tags = "@Smoke",
        // Reporting plugins for different formats
        plugin = {
                "pretty",
                "html:target/smoke_cucumber_reports/smoke_cucumber.html",
                "json:target/smoke_cucumber_reports/smoke_cucumber.json",
                "junit:target/smoke_cucumber_reports/smoke_cucumber.xml",
        },
        // Removes unnecessary characters from console output
        monochrome = true
)
public class SmokeBDDTestRunner extends AbstractTestNGCucumberTests {
}
