package com.cts.policy_bazaar.stepdefinitions;

import com.cts.policy_bazaar.frameworkutils.ReadAndWriteFromExcel;
import com.cts.policy_bazaar.pageobjects.HomePage;
import com.cts.policy_bazaar.pageobjects.PlansPage;
import com.cts.policy_bazaar.pageobjects.TravelInsurancePage;
import io.cucumber.java.en.*;
import org.testng.Assert;
import java.util.Map;
import static com.cts.policy_bazaar.stepdefinitions.Hooks.*;

public class TravelInsuranceStepsFromExcel {

    Map<String, String> testData;

    @Given("I load test data for {string}")
    public void i_load_test_data(String tcId) {
        testData = ReadAndWriteFromExcel.getTestData(tcId);
    }

    @Given("I am on the home page")
    public void i_am_on_the_home_page() {
        hp = new HomePage(driver);
    }

    @When("I click on the Travel Insurance link")
    public void i_click_on_travel_insurance() {
        hp.clickOnTravelInsurance();
        tp = new TravelInsurancePage(driver);
    }

    @Then("I should be on the travel insurance page")
    public void i_should_be_on_travel_insurance_page() {
        String actual = driver.getTitle();
        String expected = tp.getTitle();
        Assert.assertEquals(actual, expected);
    }

    @Given("I am on the travel insurance page")
    public void i_am_on_travel_insurance_page_direct() {
        hp = new HomePage(driver);
        hp.clickOnTravelInsurance();
        tp = new TravelInsurancePage(driver);
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

    @When("I click on view plans")
    public void i_click_on_view_plans() {
        tp.clickOnViewPlansButton();
        pp = new PlansPage(driver);
    }

    @Then("I should be on the plans page")
    public void i_should_be_on_the_plans_page() {
        Assert.assertTrue(pp.plansPageDisplayed());
    }

    @When("I filter by student plans from data")
    public void i_filter_by_student_plans_from_data() {
        String duration = testData.get("Test Data7");
        pp.clickOnStudentPlans();
        pp.selectBothStudents();
        pp.selectTripDuration(duration);
        pp.clickOnApplyButton();
    }

    @Then("student plans should be displayed")
    public void student_plans_should_be_displayed() {
        Assert.assertTrue(pp.studentsPlansDisplayed());
    }

    @When("I sort plans from low to high")
    public void i_sort_plans_from_low_to_high() {
        pp.clickOnSortDropDownButton();
        pp.clickOnLowToHighButton();
    }

    @Then("plans should be sorted in ascending order")
    public void plans_should_be_sorted_in_ascending_order() {
        Assert.assertTrue(pp.lowToHighBtnSelected());
    }

    @When("I fetch top 3 plans")
    public void i_fetch_top_3_plans() {
        pp.getInsuranceCompanyName();
        pp.getInsurancePrice();
    }

    @Then("I should get company names and insurance amounts")
    public void i_should_get_company_names_and_insurance_amounts() {
        ReadAndWriteFromExcel.writeDataForScenarios(pp.getInsuranceCompanyName(),"Insurance Company Name", 0, "testdata/Scenario1_Output.xlsx");
        ReadAndWriteFromExcel.writeDataForScenarios(pp.getInsurancePrice(),"Insurance Amount",1,"testdata/Scenario1_Output.xlsx");
        Assert.assertTrue(pp.getInsuranceCompanyName().size() > 0);
        Assert.assertTrue(pp.getInsurancePrice().size() > 0);
    }

    @When("I click cut button")
    public void i_click_cut_button() {
        tp.clickCutButton();
    }

    @Then("I should see error message from data")
    public void i_should_see_error_message_from_data() {
        String expectedMsg=testData.get("Test Data8");
        String actualMsg = tp.getErrorMessage();
        Assert.assertEquals(actualMsg, expectedMsg);
    }

    @Then("I should see error message of country from data")
    public void i_should_see_error_message_of_country_from_data() {
        String country=testData.get("Test Data1");
        boolean result = tp.putCountryNameInSearchBox(country);
        Assert.assertFalse(result);
    }
}