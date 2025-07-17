package com.cts.policy_bazaar.stepdefinitions;

import com.cts.policy_bazaar.frameworkutils.TestDataContext;
import com.cts.policy_bazaar.pageobjects.FormPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class FormSteps {

    FormPage formPage = new FormPage(Hooks.driver);

    @Then("form page should be displayed")
    public void form_page_displayed() {
        Assert.assertTrue(formPage.isFormPageDisplayed());
    }

    @When("user enters name and phone from data")
    public void user_enters_name_and_phone_from_data() {
        formPage.enterDetails(TestDataContext.get("Test Data6"), TestDataContext.get("Test Data7"));
    }

    @Then("invalid phone error message should be visible")
    public void invalid_phone_error_message_should_be_visible() {
        Assert.assertFalse(formPage.isSubmitEnabled());
    }
}

