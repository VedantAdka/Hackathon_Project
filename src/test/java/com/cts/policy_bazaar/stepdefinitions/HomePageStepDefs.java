package com.cts.policy_bazaar.stepdefinitions;

import com.cts.policy_bazaar.pageobjects.HomePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

import java.util.Map;

import static com.cts.policy_bazaar.stepdefinitions.Hooks.*;

public class HomePageStepDefs {

    Map<String, String> testData;

    @Given("I am on the home page")
    public void i_am_on_the_home_page() {
        hp = new HomePage(driver);
    }

    @When("I click on the Travel Insurance link")
    public void i_click_on_travel_insurance() {
        hp.clickOnTravelInsurance();
        tp = new com.cts.policy_bazaar.pageobjects.TravelInsurancePage(driver);
    }

    @Given("I am on the travel insurance page")
    public void i_am_on_travel_insurance_page_direct() {
        hp = new HomePage(driver);
        hp.clickOnTravelInsurance();
        tp = new com.cts.policy_bazaar.pageobjects.TravelInsurancePage(driver);
    }

}