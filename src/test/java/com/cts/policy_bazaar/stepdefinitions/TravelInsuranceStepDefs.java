package com.cts.policy_bazaar.stepdefinitions;

import com.cts.policy_bazaar.frameworkutils.ReadAndWriteFromExcel;
import com.cts.policy_bazaar.frameworkutils.TestDataContext;
import io.cucumber.java.en.*;
import org.testng.Assert;

import java.util.Map;

import static com.cts.policy_bazaar.stepdefinitions.Hooks.*;

public class TravelInsuranceStepDefs {

    Map<String, String> testData;

    @Given("I load test data for {string}")
    public void i_load_test_data(String tcId) {
        testData = ReadAndWriteFromExcel.getTestData(tcId);
        TestDataContext.setTcId(tcId);
        TestDataContext.setTestData(testData);
    }

    @Then("I should be on the travel insurance page")
    public void i_should_be_on_travel_insurance_page() {
        String actual = driver.getTitle();
        String expected = tp.getTitle();
        Assert.assertEquals(actual, expected);
    }

    @When("I enter destination from data")
    public void i_enter_destination_from_data() {
        tp.putCountryNameInSearchBox(testData.get("Test Data1"));
    }

    @Then("the selected destination should be correct")
    public void destination_should_be_correct() {
        Assert.assertEquals(tp.getCountryNameSelectedInSearchBox(), testData.get("Test Data1"));
    }

    @When("I select travel dates from data")
    public void i_select_travel_dates_from_data() {
        tp.clickOnStartDate();
        tp.pickStartDateAndEndDate(testData.get("Test Data2"), testData.get("Test Data3"));
    }

    @Then("the selected start date should be correct")
    public void selected_start_date_should_be_correct() {
        Assert.assertTrue(tp.getSelectedStartAndEndDate()[0].contains(testData.get("Test Data2")));
    }

    @When("I select traveller ages from data")
    public void i_select_traveller_ages_from_data() {
        tp.clickOnAddTraveller();
        tp.clickOnNoOfTraveller();
        tp.selectAgeOfFirstStudent(testData.get("Test Data4"));
        tp.selectAgeOfSecondStudent(testData.get("Test Data5"));
    }

    @When("I submit the traveller details")
    public void i_submit_the_traveller_details() {
        tp.clickOnNoButton();
        tp.clickOnSubmitButton();
    }

    @Then("I should see traveller message")
    public void i_should_see_traveller_message() {
        Assert.assertEquals(tp.getNoOfTravellerMsg(), testData.get("Test Data6"));
    }

    @When("I click cut button")
    public void i_click_cut_button() {
        tp.clickCutButton();
    }

    @Then("I should see error message from data")
    public void i_should_see_error_message_from_data() {
        Assert.assertEquals(tp.getErrorMessage(), testData.get("Test Data8"));
    }

    @Then("I should see error message of country from data")
    public void i_should_see_error_message_of_country_from_data() {
        boolean result = tp.putCountryNameInSearchBox(testData.get("Test Data1"));
        Assert.assertFalse(result);
    }
}
