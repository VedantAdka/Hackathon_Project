package com.cts.policy_bazaar.stepdefinitions;

import com.cts.policy_bazaar.pageobjects.CarInsuranceLandingPage;
import com.cts.policy_bazaar.stepdefinitions.Hooks;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class CarInsuranceLandingSteps {

    CarInsuranceLandingPage landingPage = new CarInsuranceLandingPage(Hooks.driver);

    @Then("registration input field should be visible")
    public void registration_input_should_be_visible() {
        Assert.assertTrue(landingPage.isCarRegTextBoxDisplayed());
    }

    @When("user clicks on Click here without car number")
    public void user_clicks_click_here() {
        landingPage.clickClickHereWithoutCarNumber();
    }
}
