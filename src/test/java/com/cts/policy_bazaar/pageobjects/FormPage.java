package com.cts.policy_bazaar.pageobjects;

import com.cts.policy_bazaar.frameworkutils.CommonUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FormPage extends BasePage {

    WebDriverWait wait;
    JavascriptExecutor js;
    Actions act;

    public FormPage(WebDriver driver) {
        super(driver);
        act= new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        js = (JavascriptExecutor) driver;
    }

    @FindBy(xpath = "//div[text()='Almost done! Just one last step']")
    WebElement formPageHeader;

    @FindBy(xpath = "//div[text()='Delivery details']")
    WebElement deliveryDetailsHeader;

    @FindBy(xpath = "//input[@placeholder='Full name']")
    WebElement nameInput;

    @FindBy(xpath = "//input[@placeholder='Mobile number']")
    WebElement mobileInput;

    @FindBy(xpath = "//button[@class='primaryBtnV2 width-100']")
    WebElement submitButton;

    public boolean isFormPageDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            return wait.until(ExpectedConditions.visibilityOf(formPageHeader)).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }


    public void enterDetails(String name, String mobile) {
        CommonUtils.sureWait(2);
        wait.until(ExpectedConditions.elementToBeClickable(nameInput)).clear();
        nameInput.sendKeys(name);

        wait.until(ExpectedConditions.elementToBeClickable(mobileInput)).clear();
        mobileInput.sendKeys(mobile);
        CommonUtils.sureWait(2);
    }

    public boolean isSubmitEnabled() {
        CommonUtils.sureWait(2);
        return submitButton.isEnabled();
    }
}
