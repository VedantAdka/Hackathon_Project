package com.cts.policy_bazaar.stepdefinitions;

import com.cts.policy_bazaar.pageobjects.HomePage;
import com.cts.policy_bazaar.pageobjects.PlansPage;
import com.cts.policy_bazaar.pageobjects.TravelInsurancePage;
import io.cucumber.java.en.*;
import org.testng.Assert;
import static com.cts.policy_bazaar.stepdefinitions.Hooks.*;

public class TravelInsuranceSteps {

    @Given("I am on the home page")
    public void i_am_on_the_home_page() throws Exception{
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
        Assert.assertEquals(expected, actual);
    }

    @Given("I am on the travel insurance page")
    public void i_am_on_travel_insurance_page_direct() throws Exception {
        i_am_on_the_home_page();
        i_click_on_travel_insurance();
    }

    @When("I enter {string} as the destination")
    public void i_enter_as_destination(String country) {
        tp.putCountryNameInSearchBox(country);
    }

    @Then("the selected destination should be {string}")
    public void the_selected_destination_should_be(String expectedCountry) {
        Assert.assertEquals(expectedCountry, tp.getCountryNameSelectedInSearchBox());
    }

    @When("I select {string} as the start date and {string} as the end date")
    public void i_select_dates(String start, String end) {
        tp.clickOnStartDate();
        tp.pickStartDateAndEndDate(start, end);
    }

    @Then("the selected start date should contain {string}")
    public void selected_start_date_should_contain(String expectedStart) {
        Assert.assertTrue(tp.getSelectedStartAndEndDate()[0].contains(expectedStart));
    }

    @When("I select {string} as age of first traveller and {string} as age of second traveller")
    public void i_select_ages(String age1, String age2) {
        tp.clickOnAddTraveller();
        tp.clickOnNoOfTraveller();
        tp.selectAgeOfFirstStudent(age1);
        tp.selectAgeOfSecondStudent(age2);
    }

    @When("I submit the traveller details")
    public void i_submit_traveller_details() {
        tp.clickOnNoButton();
        tp.clickOnSubmitButton();
    }

    @Then("I should see message {string}")
    public void i_should_see_message(String message) {
        Assert.assertEquals(message, tp.getNoOfTravellerMsg());
    }

    @When("I click on view plans")
    public void i_click_on_view_plans() {
        tp.clickOnViewPlansButton();
        pp = new PlansPage(driver);
    }

    @Then("I should be on the plans page")
    public void i_should_be_on_plans_page() {
        Assert.assertTrue(pp.plansPageDisplayed());
    }

    @When("I filter by student plans with duration {string}")
    public void i_filter_by_student_plans(String duration) {
        pp.clickOnStudentPlans();
        pp.selectBothStudents();
        pp.selectTripDuration(duration);
        pp.clickOnApplyButton();
    }

    @Then("student plans should be displayed")
    public void student_plans_displayed() {
        Assert.assertTrue(pp.studentsPlansDisplayed());
    }

    @When("I sort plans from low to high")
    public void i_sort_plans_low_to_high() {
        pp.clickOnSortDropDownButton();
        pp.clickOnLowToHighButton();
    }

    @Then("plans should be sorted in ascending order")
    public void plans_sorted_low_to_high() {
        Assert.assertTrue(pp.lowToHighBtnSelected());
    }

    @When("I fetch top 3 plans")
    public void i_fetch_top_3_plans() {
        // This is typically a read action
        pp.getInsuranceCompanyName();
        pp.getInsurancePrice();
    }

    @Then("I should get company names and insurance amounts")
    public void i_should_get_company_names_and_prices() {
        Assert.assertTrue(pp.getInsuranceCompanyName().size() > 0);
        Assert.assertTrue(pp.getInsurancePrice().size() > 0);
    }

    @When("I click cut button")
    public void i_click_cut_button() {
        tp.clickCutButton();
    }

    @Then("I should see error message {string}")
    public void i_should_see_error_message(String msg) {
        Assert.assertEquals(msg, tp.getErrorMessage());
    }

    @Then("I should see no result found error")
    public void i_should_see_no_result_found_error() {
        Assert.assertFalse(tp.putCountryNameInSearchBox("ZZZ"));
    }
}