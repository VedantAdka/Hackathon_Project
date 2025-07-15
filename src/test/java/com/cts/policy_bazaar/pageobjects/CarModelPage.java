package com.cts.policy_bazaar.pageobjects;

import com.cts.policy_bazaar.frameworkutils.CommonUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CarModelPage extends BasePage {

    WebDriverWait wait;
    Actions actions;
    JavascriptExecutor js;

    public CarModelPage(WebDriver driver) {
        super(driver);
        actions = new Actions(driver);
        js = (JavascriptExecutor) driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @FindBy(xpath = "//input[@placeholder='Search Car Model']")
    WebElement searchModelInput;

    @FindBy(xpath = "//div[@class='options open']/div")
    List<WebElement> modelList;

    @FindBy(xpath = "//div[text()='Select Car Fuel Type']")
    WebElement fuelTypeHeader;

    @FindBy(xpath = "//div[@class='carFuelWrap']/div/ul/li")
    List<WebElement> fuelTypes;

    @FindBy(xpath = "//div[text()='Select Car Variant']")
    WebElement variantHeader;

    @FindBy(xpath = "//ul[@class='gridList slideToLeft mb-24']/li")
    List<WebElement> variants;

    public void searchModel(String model) {
        CommonUtils.sureWait(2);
        searchModelInput.sendKeys(model);
        for (WebElement m : modelList) {
            if (m.getText().equalsIgnoreCase("Grand i10")) {
                wait.until(ExpectedConditions.elementToBeClickable(m)).click();
                break;
            }
        }
        CommonUtils.sureWait(2);
    }

    public void selectFuel(String fuel) {
        CommonUtils.sureWait(2);
        for (WebElement f : fuelTypes) {
            if (f.getText().equalsIgnoreCase(fuel)) {
                wait.until(ExpectedConditions.elementToBeClickable(f)).click();
                break;
            }
        }
        CommonUtils.sureWait(2);
    }

    public void selectVariant(String variant) {
        CommonUtils.sureWait(2);
        for (WebElement v : variants) {
            if (v.getText().equalsIgnoreCase(variant)) {
                wait.until(ExpectedConditions.elementToBeClickable(v)).click();
                break;
            }
        }
        CommonUtils.sureWait(2);
    }

    public boolean isSearchModelInputDisplayed() {
        CommonUtils.sureWait(2);
        return wait.until(ExpectedConditions.visibilityOf(searchModelInput)).isDisplayed();
    }

    public boolean isFuelTypeHeaderDisplayed() {
        CommonUtils.sureWait(2);
        return wait.until(ExpectedConditions.visibilityOf(fuelTypeHeader)).isDisplayed();
    }

    public boolean isVariantHeaderDisplayed() {
        CommonUtils.sureWait(2);
        return wait.until(ExpectedConditions.visibilityOf(variantHeader)).isDisplayed();
    }
}
