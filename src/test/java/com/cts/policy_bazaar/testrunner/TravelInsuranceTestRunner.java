package com.cts.policy_bazaar.testrunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java/com/cts/policy_bazaar/features",
        glue = {"com.cts.policy_bazaar.stepdefinitions"},
        plugin = {"pretty", "html:target/cucumber-reports"},
        monochrome = true
)
public class TravelInsuranceTestRunner extends AbstractTestNGCucumberTests {
}
