package com.cts.policy_bazaar.pageobjects;

import com.cts.policy_bazaar.frameworkutils.CommonUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class CarInsuranceLandingPage extends BasePage {

    WebDriverWait wait;

    public CarInsuranceLandingPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @FindBy(xpath = "//input[@id='regNoTextBox']")
    WebElement regNoTextBox;

    @FindBy(xpath = "//span[text()='Click here ']")
    WebElement clickHereLink;

    public boolean isCarRegTextBoxDisplayed() {
        CommonUtils.sureWait(2);
        return wait.until(ExpectedConditions.visibilityOf(regNoTextBox)).isDisplayed();
    }

    public void clickClickHereWithoutCarNumber() {
        CommonUtils.sureWait(2);
        wait.until(ExpectedConditions.elementToBeClickable(clickHereLink)).click();
        CommonUtils.sureWait(2);
    }
}