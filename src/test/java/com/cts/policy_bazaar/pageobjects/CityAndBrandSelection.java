package com.cts.policy_bazaar.pageobjects;

import com.cts.policy_bazaar.frameworkutils.CommonUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CityAndBrandSelection extends BasePage {

    WebDriverWait wait;

    public CityAndBrandSelection(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @FindBy(xpath = "//div[text()='Select City']")
    WebElement selectCityText;

    @FindBy(xpath = "//ul/li/span[1]")
    List<WebElement> cityOptions;

    @FindBy(xpath = "//div[text()='Select Car Brand']")
    WebElement selectCarBrandText;

    @FindBy(xpath = "//ul/li/span[2]")
    List<WebElement> brandOptions;

    public boolean isCityTextVisible() {
        CommonUtils.sureWait(2);
        return wait.until(ExpectedConditions.visibilityOf(selectCityText)).isDisplayed();
    }

    public void selectCity(String city) {
        CommonUtils.sureWait(2);
        for (WebElement c : cityOptions) {
            if (c.getText().equalsIgnoreCase(city)) {
                wait.until(ExpectedConditions.elementToBeClickable(c)).click();
                break;
            }
        }
        CommonUtils.sureWait(2);
    }

    public boolean isCarBrandSectionDisplayed() {
        CommonUtils.sureWait(2);
        return wait.until(ExpectedConditions.visibilityOf(selectCarBrandText)).isDisplayed();
    }

    public void selectBrand(String brand) {
        CommonUtils.sureWait(2);
        for (WebElement b : brandOptions) {
            if (b.getText().equalsIgnoreCase(brand)) {
                wait.until(ExpectedConditions.elementToBeClickable(b)).click();
                break;
            }
        }
        CommonUtils.sureWait(2);
    }
}
