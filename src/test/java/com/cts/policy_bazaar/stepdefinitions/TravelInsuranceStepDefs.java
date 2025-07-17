package com.cts.policy_bazaar.stepdefinitions;

import com.cts.policy_bazaar.frameworkutils.ReadAndWriteFromExcel;
import com.cts.policy_bazaar.pageobjects.TravelInsurancePage;
import io.cucumber.java.en.*;
import lombok.Locked;
import org.testng.Assert;

import java.util.Map;

import static com.cts.policy_bazaar.stepdefinitions.Hooks.*;

public class TravelInsuranceStepDefs {

    Map<String, String> testData;

    @Given("I load test data for {string}")
    public void i_load_test_data(String tcId) {
        TestDataContext.setTcId(tcId);
        testData= ReadAndWriteFromExcel.getTestData(tcId);
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
        String country = testData.get("Test Data1");
        tp.putCountryNameInSearchBox(country);
    }

    @Then("the selected destination should be correct")
    public void destination_should_be_correct() {
        String expected = testData.get("Test Data1");
        String actual = tp.getCountryNameSelectedInSearchBox();
        Assert.assertEquals(actual, expected);
    }

    @When("I select travel dates from data")
    public void i_select_travel_dates_from_data() {
        String startDate = testData.get("Test Data2");
        String endDate = testData.get("Test Data3");
        tp.clickOnStartDate();
        tp.pickStartDateAndEndDate(startDate, endDate);
    }

    @Then("the selected start date should be correct")
    public void selected_start_date_should_be_correct() {
        String expectedStart = testData.get("Test Data2");
        Assert.assertTrue(tp.getSelectedStartAndEndDate()[0].contains(expectedStart));
    }

    @When("I select traveller ages from data")
    public void i_select_traveller_ages_from_data() {
        String age1 = testData.get("Test Data4");
        String age2 = testData.get("Test Data5");
        tp.clickOnAddTraveller();
        tp.clickOnNoOfTraveller();
        tp.selectAgeOfFirstStudent(age1);
        tp.selectAgeOfSecondStudent(age2);
    }

    @When("I submit the traveller details")
    public void i_submit_the_traveller_details() {
        tp.clickOnNoButton();
        tp.clickOnSubmitButton();
    }

    @Then("I should see traveller message")
    public void i_should_see_traveller_message() {
        String expectedMsg = testData.get("Test Data6");
        String actualMsg = tp.getNoOfTravellerMsg();
        Assert.assertEquals(actualMsg, expectedMsg);
    }

    @When("I click cut button")
    public void i_click_cut_button() {
        tp.clickCutButton();
    }

    @Then("I should see error message from data")
    public void i_should_see_error_message_from_data() {
        String expectedMsg = testData.get("Test Data8");
        String actualMsg = tp.getErrorMessage();
        Assert.assertEquals(actualMsg, expectedMsg);
    }

    @Then("I should see error message of country from data")
    public void i_should_see_error_message_of_country_from_data() {
        String country = testData.get("Test Data1");
        boolean result = tp.putCountryNameInSearchBox(country);
        Assert.assertFalse(result);
    }
}