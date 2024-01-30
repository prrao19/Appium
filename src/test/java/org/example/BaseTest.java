package org.example;

import com.beust.jcommander.Parameter;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumDriver;
//import io.appium.java_client.MobileElement;
import io.appium.java_client.MobileCommand;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.MobilePlatform;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.*;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {

//    public AndroidDriver driver;
//    public AndroidDriver driverNew;

    public ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();
    public AppiumDriverLocalService service;
    final String myPlatform = "web";

    public void setDriver(AppiumDriver driver){
        this.driver.set(driver);
    }

    public AppiumDriver getDriver(){
        return this.driver.get();
    }

    @Parameters({"platform","runOn"})
    @BeforeClass
    public void configureAppium(@Optional(myPlatform)String platform,@Optional("chrome_normal")String runOn) throws MalformedURLException
    {
        UiAutomator2Options options = new UiAutomator2Options();
//        DesiredCapabilities capabilities = new DesiredCapabilities();
        if(runOn.equals("emulator-5556"))
        {
            options.setDeviceName("Pixel");
            options.setCapability("udid", "emulator-5556");
            options.setApp("D://Mobile Automation//Appium Project//Appium//src//test//java//org//example//Resources//General-Store.apk");
            setDriver(new AndroidDriver( new URL("http://127.0.0.1:4727"), options));
        }
        else if(runOn.equals("emulator-5554")){
            options.setDeviceName("Pramod");
            options.setCapability("udid", "emulator-5554");
            options.setApp("D://Mobile Automation//Appium Project//Appium//src//test//java//org//example//Resources//General-Store.apk");
            setDriver(new AndroidDriver( new URL("http://127.0.0.1:4729"), options));
//            driver = new AndroidDriver(new URL("http://127.0.0.1:4729"), options);
        } else {
            service = new AppiumServiceBuilder().withAppiumJS(new File("C://Users//prrao//AppData//Roaming//npm//node_modules//appium//build//lib//main.js")).withIPAddress("127.0.0.1").usingPort(4723).build();
        service.start();
//        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("Pramod");
        options.setChromedriverExecutable("D://Mobile Automation//Appium Project//Appium//src//test//java//org//example//Resources//chromedriver.exe");
        options.setApp("D://Mobile Automation//Appium Project//Appium//src//test//java//org//example//Resources//General-Store.apk");
        setDriver(new AndroidDriver( new URL("http://127.0.0.1:4723"), options));
//        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        }
    }

    public void longPressAction(WebElement ele)
    {
        ((JavascriptExecutor) driver).executeScript("mobile: longClickGesture", ImmutableMap.of("elementId", ((RemoteWebElement)ele).getId(), "duration", 2000));
    }

    public void scrollToEnd(){
        boolean canScrollMore;
        do{
            canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
                    "left", 100, "top", 100, "width", 200, "height", 200,
                    "direction", "down",
                    "percent", 3.0
            ));
        }while (canScrollMore);
    }

    public void swipeAction(WebElement ele, String direction){
        ((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement)ele).getId(),
                "direction", direction,
                "percent", 0.75
        ));
    }

    @AfterClass
    public void tearDown()
    {
//        driver.quit();
        getDriver().quit();
//        service.stop();
    }
}