package org.example;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Set;

public class Cart extends AndroidActions{

    AndroidDriver driver;

    public Cart(AndroidDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id="com.androidsample.generalstore:id/termsButton")
    private WebElement terms;

    @AndroidFindBy(id="android:id/button1")
    private WebElement termsCloseButton;

    @AndroidFindBy(className="android.widget.CheckBox")
    private WebElement checkBox;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/btnProceed")
    private WebElement purchaseButton;

    public void clickTerms() throws InterruptedException {
        longPressAction(terms);
    }

    public void closeTermPopup()
    {
        termsCloseButton.click();
    }

    public void clickCheckBox()
    {
        checkBox.click();
    }

    public void completePurchase()
    {
        purchaseButton.click();
    }

    public double getTotalAmount(){
        List<WebElement> productPrices = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice"));
        int count = productPrices.size();
        double totalSum = 0;
        for(int i=0; i<count; i++){
            String amountString = productPrices.get(i).getText();
            Double price = Double.parseDouble(amountString.substring(1));
            totalSum = totalSum + price;
        }
        return totalSum;
    }

    public double getDisplayFormattedAmount()
    {
        String displaysum = driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
        double displayformattedsum = Double.parseDouble(displaysum.substring(1));
        return displayformattedsum;
    }

    public void getContext(){
        Set<String> contexts = driver.getContextHandles();
        for(String contextName:contexts)
        {
            System.out.println("Contexts_List:"+contextName);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            driver.context("WEBVIEW");
            driver.findElement(By.name("q")).sendKeys("apple");
            driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
            driver.pressKey(new KeyEvent(AndroidKey.BACK));
            driver.context("NATIVE_APP");
        }
    }



}
