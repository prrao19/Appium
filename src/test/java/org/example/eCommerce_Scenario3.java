package org.example;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.example.Extent.TestConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.List;


public class eCommerce_Scenario3 extends BaseTest{

    @Test
    public void validateScenario3_Amount() throws InterruptedException {
        ProductCatalog productCatalog = new ProductCatalog((AndroidDriver) getDriver());
        FormPage formPage = new FormPage((AndroidDriver) getDriver());
        Cart cart = new Cart((AndroidDriver) getDriver());
        Thread.sleep(3000);
        formPage.setNameField(TestConstants.LOGINNAME);
        formPage.setGender(TestConstants.GENDER_FEMALE);
        formPage.setCountrySelection(TestConstants.COUNTRY_ARGENTINA);
        formPage.clickSubmit();
        Thread.sleep(2000);
        productCatalog.addItemtoCartByIndex(0);
        productCatalog.addItemtoCartByIndex(0);
        productCatalog.goToCart();
        productCatalog.waitForCartToLoad();
        double totalSum = cart.getTotalAmount();
        double displayformattedsum = cart.getDisplayFormattedAmount();
        Assert.assertEquals(totalSum, displayformattedsum);
    }

}
