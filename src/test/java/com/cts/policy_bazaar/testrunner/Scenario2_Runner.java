package com.cts.policy_bazaar.testrunner;

import com.cts.policy_bazaar.browserutils.BrowserFactory;
import com.cts.policy_bazaar.frameworkutils.PropertiesFileReader;
import com.cts.policy_bazaar.seleniumutils.ScreenShotUtil;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;



public class Scenario2_Runner {

    public void test() throws Exception {
        String browser = PropertiesFileReader.getPropertyValue("config", "browsername");
        String url = PropertiesFileReader.getPropertyValue("config", "url");
        String remoteIP=PropertiesFileReader.getPropertyValue("config","hubIP");
        String wr=PropertiesFileReader.getPropertyValue("config","whereToRun");
        WebDriver driver = BrowserFactory.getBrowser(browser, wr, remoteIP);
        BrowserFactory.OpenUrl(url);
        WebElement Car = driver.findElement(By.xpath("//p[text()='Car']"));
        Actions action=new Actions(driver);
        action.moveToElement(Car).click().build().perform();
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
        JavascriptExecutor jse=(JavascriptExecutor)driver;
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//span[text()='Click here ']")))).click();

        List<WebElement> citySearch=driver.findElements(By.xpath("//ul/li/span[1]"));
        for(WebElement i:citySearch){
            if(i.getText().equalsIgnoreCase("Delhi")){
                i.click();
                break;
            }
        }
        List<WebElement> carbrand=driver.findElements(By.xpath("//ul/li/span[2]"));
        for(WebElement i:carbrand){
            if(i.getText().equalsIgnoreCase("HYUNDAI")){
                i.click();
                break;
            }
        }

        WebElement searchCarModel = driver.findElement(By.xpath("//input[@placeholder='Search Car Model']"));
        action.moveToElement(searchCarModel).click().build().perform();

        jse.executeScript("arguments[0].value='Grand '",searchCarModel);
        action.sendKeys(Keys.SPACE).perform();
        List<WebElement> carmodel=driver.findElements(By.xpath("//div[@class='options open']/div"));
        for(WebElement i:carmodel){
            if(i.getText().equalsIgnoreCase("Grand i10")){
                i.click();
                break;
            }
        }

        List<WebElement> carfueltype=driver.findElements(By.xpath("//div[@class='carFuelWrap']/div/ul/li"));
        for(WebElement i:carfueltype){
            if(i.getText().equalsIgnoreCase("Petrol")){
                i.click();
                break;
            }
        }

        List<WebElement> carvariant=driver.findElements(By.xpath("//ul[@class='gridList slideToLeft mb-24']/li"));
        for(WebElement i:carvariant){
            if(i.getText().equalsIgnoreCase("1.2 SPORT+ (1197 cc)")){
                i.click();
                break;
            }
        }

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@placeholder='Full name']")))).sendKeys("Sushant Gargi");
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@placeholder='Mobile number']")))).sendKeys("3473923890");

        WebElement submitButton = driver.findElement(By.xpath("//button[@class='primaryBtnV2 width-100']"));

        if (!submitButton.isEnabled()) {
            System.out.println("Test passed: Submit button is disabled due to invalid mobile number.");
            ScreenShotUtil.takeScreenShot(driver);
        } else {
            System.out.println("Test failed: Submit button is enabled even with invalid mobile number.");
            throw new AssertionError("Submit button should be disabled for invalid input.");
        }
        driver.close();
    }
}
