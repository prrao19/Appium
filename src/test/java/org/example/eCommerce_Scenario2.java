package org.example;


import io.appium.java_client.android.AndroidDriver;
import org.example.Extent.TestConstants;
import org.testng.Assert;
import org.testng.annotations.Test;


public class eCommerce_Scenario2 extends BaseTest{

    @Test
    public void validateScenario2() throws InterruptedException {
        FormPage formPage = new FormPage((AndroidDriver) getDriver());
        ProductCatalog productCatalog = new ProductCatalog((AndroidDriver) getDriver());
        Thread.sleep(3000);
        formPage.setNameField(TestConstants.LOGINNAME);
        formPage.setGender(TestConstants.GENDER_FEMALE);
        formPage.setCountrySelection(TestConstants.COUNTRY_ARGENTINA);
        formPage.clickSubmit();
        Thread.sleep(2000);
        productCatalog.scrollToProduct(TestConstants.PRODUCT_NAME);
        productCatalog.selectProduct(TestConstants.PRODUCT_NAME);
        productCatalog.goToCart();
        productCatalog.waitForCartToLoad();
        String lastPageProduct = productCatalog.getProductFromCart();
        Assert.assertEquals(lastPageProduct,TestConstants.PRODUCT_NAME);
    }
}
