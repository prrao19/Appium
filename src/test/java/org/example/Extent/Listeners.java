package org.example.Extent;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.appium.java_client.AppiumDriver;
import org.example.AppiumUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGListener;
import org.testng.ITestResult;

import java.io.IOException;

public class Listeners implements ITestListener {

    ExtentTest test;
    ExtentReports extent = ExtentReporterNG.getReporterObject();
    AppiumDriver driver;

    @Override
    public void onTestStart(ITestResult result) {
        // TODO Auto-generated method stub
        test= extent.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // TODO Auto-generated method stub
        test.log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.fail(result.getThrowable());
    }

    @Override
    public void onFinish(ITestContext context) {
        // TODO Auto-generated method stub
        extent.flush();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.skip(result.getThrowable());
    }

}
