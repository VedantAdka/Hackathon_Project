package com.cts.policy_bazaar.testrunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java/com/cts/policy_bazaar/features",
        glue = {"com.cts.policy_bazaar.stepdefinitions", "com.cts.policy_bazaar.frameworkutils"},
        plugin = {
                "pretty",
                "html:target/cucumber_reports/cucumber.html",
                "json:target/cucumber_reports/cucumber.json",
                "junit:target/cucumber_reports/cucumber.xml"
        },
        monochrome = true
)
public class TravelInsuranceTestRunner extends AbstractTestNGCucumberTests {
}
