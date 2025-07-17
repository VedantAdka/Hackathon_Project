package com.cts.policy_bazaar.stepdefinitions;

import com.cts.policy_bazaar.frameworkutils.TestDataContext;
import com.cts.policy_bazaar.pageobjects.CityAndBrandSelection;
import com.cts.policy_bazaar.stepdefinitions.Hooks;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class SelectionSteps {

    CityAndBrandSelection selectionPage = new CityAndBrandSelection(Hooks.driver);

    @Then("city selection field should be visible")
    public void city_field_visible() {
        Assert.assertTrue(selectionPage.isCityTextVisible());
    }

    @When("user selects city from data")
    public void user_selects_city_from_data() {
        selectionPage.selectCity(TestDataContext.get("Test Data1"));
    }

    @Then("brand selection field should be visible")
    public void brand_field_visible() {
        Assert.assertTrue(selectionPage.isCarBrandSectionDisplayed());
    }

    @When("user selects brand from data")
    public void user_selects_brand_from_data() {
        selectionPage.selectBrand(TestDataContext.get("Test Data2"));
    }
}