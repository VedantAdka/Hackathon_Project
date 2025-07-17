package com.cts.policy_bazaar.stepdefinitions;

import com.cts.policy_bazaar.frameworkutils.TestDataContext;
import com.cts.policy_bazaar.pageobjects.CarModelPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class CarModelSteps {

    CarModelPage modelPage = new CarModelPage(Hooks.driver);

    @Then("model search input should be visible")
    public void model_search_input_should_be_visible() {
        Assert.assertTrue(modelPage.isSearchModelInputDisplayed());
    }

    @When("user selects model from data")
    public void user_selects_model_from_data() {
        modelPage.searchModel(TestDataContext.get("Test Data3"));
    }

    @Then("fuel type field should be visible")
    public void fuel_type_visible() {
        Assert.assertTrue(modelPage.isFuelTypeHeaderDisplayed());
    }

    @When("user selects fuel type from data")
    public void user_selects_fuel_from_data() {
        modelPage.selectFuel(TestDataContext.get("Test Data4"));
    }

    @Then("variant field should be visible")
    public void variant_visible() {
        Assert.assertTrue(modelPage.isVariantHeaderDisplayed());
    }

    @When("user selects variant from data")
    public void user_selects_variant_from_data() {
        modelPage.selectVariant(TestDataContext.get("Test Data5"));
    }
}
