package com.cts.policy_bazaar.stepdefinitions;

import com.cts.policy_bazaar.frameworkutils.ReadAndWriteFromExcel;
import com.cts.policy_bazaar.frameworkutils.TestDataContext;
import com.cts.policy_bazaar.pageobjects.PlansPage;
import io.cucumber.java.en.*;
import org.testng.Assert;

import java.util.Map;

import static com.cts.policy_bazaar.stepdefinitions.Hooks.*;

public class PlansPageStepDefs {

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
        Map<String, String> testData = TestDataContext.getTestData();
        pp.clickOnStudentPlans();
        pp.selectBothStudents();
        pp.selectTripDuration(testData.get("Test Data7"));
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
        ReadAndWriteFromExcel.writeDataForScenarios(pp.getInsuranceCompanyName(), "Insurance Company Name", 0, "testdata/Scenario1_Output.xlsx");
        ReadAndWriteFromExcel.writeDataForScenarios(pp.getInsurancePrice(), "Insurance Amount", 1, "testdata/Scenario1_Output.xlsx");
        Assert.assertTrue(pp.getInsuranceCompanyName().size() > 0);
        Assert.assertTrue(pp.getInsurancePrice().size() > 0);
    }
}
