package org.example;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumElementLocatorFactory;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class FormPage extends  AndroidActions{
    AndroidDriver driver;

    public FormPage(AndroidDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

//    driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Pramod");
    @AndroidFindBy(id="com.androidsample.generalstore:id/nameField")
    private WebElement nameField;

//    driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
    @AndroidFindBy(xpath = "//android.widget.RadioButton[@text='Female']")
    private WebElement femaleOption;

    @AndroidFindBy(xpath = "//android.widget.RadioButton[@text='male']")
    private WebElement maleOption;

    @AndroidFindBy(id="android:id/text1")
    private WebElement countrySelection;

    //driver.findElement(By.xpath("(//android.widget.Toast)[1]")).getAttribute("name");
    @AndroidFindBy(xpath = "(//android.widget.Toast)[1]")
    private WebElement toast;

    @AndroidFindBy(id="com.androidsample.generalstore:id/btnLetsShop")
    private WebElement submitButton;

    public void setNameField(String name)
    {
        nameField.sendKeys(name);
        driver.hideKeyboard();
    }

    public void setGender(String gender)
    {
        if(gender.contains("female"))
            femaleOption.click();
        else
            maleOption.click();
    }

    public void setCountrySelection(String countryName){
        countrySelection.click();
        scrollToText(countryName);
        driver.findElement(By.xpath("//android.widget.TextView[@text='"+countryName+"']")).click();
    }

    public void clickSubmit()
    {
        submitButton.click();
    }

    public String getToastMessage(){
        return toast.getAttribute("name");
    }

}
