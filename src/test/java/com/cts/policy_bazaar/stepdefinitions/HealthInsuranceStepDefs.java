package com.cts.policy_bazaar.stepdefinitions;

import com.cts.policy_bazaar.frameworkutils.ReadAndWriteFromExcel;
import com.cts.policy_bazaar.frameworkutils.TestDataContext;
import com.cts.policy_bazaar.pageobjects.HealthInsurancePage;
import com.cts.policy_bazaar.pageobjects.HomePage;
import io.cucumber.java.en.*;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

import static com.cts.policy_bazaar.stepdefinitions.Hooks.*;

public class HealthInsuranceStepDefs {

    @When("I hover over insurance products")
    public void i_hover_over_insurance_products() {
        hp.hoverToInsuranceProducts();
    }

    @Then("insurance products menu should be enabled")
    public void insurance_products_menu_should_be_enabled() {
        Assert.assertTrue(hp.insuranceProductsIsEnabled(), "Insurance Products not enabled");
    }

    @Then("health insurance link should be enabled")
    public void health_insurance_link_should_be_enabled() {
        Assert.assertTrue(hp.healthInsuranceIsEnabled(), "Health Insurance link not enabled");
    }

    @When("I click on health insurance")
    public void i_click_on_health_insurance() {
        hp.selectHealthInsurance();
        hi = new HealthInsurancePage(driver);
    }

    @Then("I should be on the health insurance page with data from excel")
    public void i_should_be_on_the_health_insurance_page() {
        String expectedTitle = TestDataContext.get("Test Data1");
        String actualTitle = hi.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle, "Did not land on expected Health Insurance page");
    }

    @When("I close the footer popup")
    public void i_close_the_footer_popup() {
        hi.clickOnCloseButton();
    }

    @When("I click on View More Plans")
    public void i_click_on_view_more_plans() {
        hi.clickOnViewMorePlansButton();
    }

    @When("I click on all View More buttons of listed plans")
    public void i_click_on_all_view_more_buttons_of_listed_plans() {
        hi.clickOnListOfViewMorePlansButtons();
    }


    @Then("I fetch insurance names, cover amounts and start amounts")
    public void i_fetch_insurance_names_cover_amounts_and_start_amounts() {
        Assert.assertTrue(hi.getInsuranceNames().size() > 0, "No insurance plans retrieved");
    }

    @Then("I store the plan details to Excel")
    public void i_store_the_plan_details_to_excel() {
        ReadAndWriteFromExcel.writeDataForScenarios(hi.getInsuranceNames(), "Insurance Name", 0, "testdata/Scenario3_Output.xlsx");
        ReadAndWriteFromExcel.writeDataForScenarios(hi.getCoverAmount(), "Cover Amount", 1, "testdata/Scenario3_Output.xlsx");
        ReadAndWriteFromExcel.writeDataForScenarios(hi.getStartAtAmount(), "Start Amount", 2, "testdata/Scenario3_Output.xlsx");
    }
}
