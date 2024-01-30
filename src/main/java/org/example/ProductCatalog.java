package org.example;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProductCatalog extends AndroidActions{

    AndroidDriver driver;

    public ProductCatalog(AndroidDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='ADD TO CART']")
    public List<WebElement> addToCart;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/appbar_btn_cart")
    public WebElement cart;
    public void addItemtoCartByIndex(int index)
    {
        addToCart.get(index).click();
    }

    public void goToCart() throws InterruptedException {
        cart.click();
        Thread.sleep(2000);
    }

    public void scrollToProduct(String productName)
    {
        scrollToText(productName);
    }

    public void selectProduct(String product)
    {
        int productCount = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();
        for(int i=0; i<productCount; i++)
        {
        String productName = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).get(i).getText();
        System.out.println("ProductName: "+productName);
        if(productName.equals(product))
        {
            driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
        }
        }
    }

    public void waitForCartToLoad()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.attributeContains(driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")), "text", "Cart"));
    }

    public String getProductFromCart()
    {
        String lastPageProduct = driver.findElement(By.id("com.androidsample.generalstore:id/productName")).getText();
        return lastPageProduct;
    }

}
