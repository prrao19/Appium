package org.example;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.example.Extent.TestConstants;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class eCommerce_Scenario1 extends BaseTest{

    ExtentReports extent;
    public AndroidDriver driver;

    @BeforeMethod
    public void preSetup() throws InterruptedException {
//        Activity activity = new Activity("com.androidsample.generalstore", "com.androidsample.generalstore.MainActivity");
//        driver.startActivity(activity);
        UiAutomator2Options options = new UiAutomator2Options();
        options.setApp("D://Mobile Automation//Appium Project//Appium//src//test//java//org//example//Resources//General-Store.apk");
    }

    @Test(dataProvider = "getData")
    public void validateErrorForm(String name, String gender, String country) throws InterruptedException {
        Thread.sleep(3000);
        FormPage formPage = new FormPage((AndroidDriver) getDriver());
        formPage.setGender(gender);
        formPage.setCountrySelection(country);
        formPage.clickSubmit();
        Thread.sleep(1000);
        String toastMessage = formPage.getToastMessage();
        Assert.assertEquals(toastMessage,TestConstants.TOAST_MESSAGE);
    }

    @Test
    public void validateFillForm() throws InterruptedException {
        Thread.sleep(3000);
        FormPage formPage = new FormPage((AndroidDriver) getDriver());
        formPage.setNameField("Pramod");
        formPage.setGender("female");
        formPage.setCountrySelection("Argentina");
        formPage.clickSubmit();
    }

    @DataProvider
    public Object[][] getData()
    {
        return new Object[][] { {TestConstants.LOGINNAME, TestConstants.GENDER_FEMALE, TestConstants.COUNTRY_ARGENTINA}};
    }
}
