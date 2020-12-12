package com.automation.tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;


import java.net.URL;

public class Calculator_App {

    AppiumDriver<MobileElement> driver;

    @Test
    public void test1(){
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        //since we use android, put android. could be IOS
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
        //version of your device
        desiredCapabilities.setCapability(MobileCapabilityType.VERSION, "7.0");
        //name of the device
        //if it's a real device, you need to use your parameter
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel_2");
        //either you can specify app --> //path/to/the/app.apk
        //or, if app is already installed, you need to specify appActivity and AppPackage
        //this info you can find in the internet, at work - from developers
        //otherwise, you can use apk info app to find this information
        desiredCapabilities.setCapability("appPackage", "com.android.calculator2");
        // Set your application's package name.
        desiredCapabilities.setCapability("appActivity", "com.android.calculator2.Calculator");
        // Set your application's MainActivity i.e. the LAUNCHER activity name.

        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");

        //"http://localhost:4723/wd/hub" - address of the appium server. If you have appium server on the same computer - just use localhost
        //4723 - default appium port.
        //we need to provide 2 parameters: URL of appium servers and desired capabilities
        // in desired capabilities we specify device and  application to test information


        try{
            driver = new AppiumDriver<>(new URL("http://localhost:4723/wd/hub"), desiredCapabilities);
        }catch (MalformedURLException e){
            e.printStackTrace();
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //WebElement or MobileElement
        MobileElement digit2 = driver.findElement(By.id("com.android.calculator2:id/digit_2"));

        MobileElement plus = driver.findElement(MobileBy.AccessibilityId("plus"));
        MobileElement minus = driver.findElement(MobileBy.AccessibilityId("minus"));
        MobileElement multiply = driver.findElement(MobileBy.AccessibilityId("multiply"));
        MobileElement divide = driver.findElement(MobileBy.AccessibilityId("divide"));
        MobileElement equals = driver.findElement(MobileBy.AccessibilityId("equals"));

        MobileElement result = driver.findElement(By.id("com.android.calculator2:id/result"));


        digit2.click();
        plus.click();
        digit2.click();
        equals.click();

        String resultText = result.getText();

        Assert.assertEquals("4", resultText);


        getDigit(8).click();
        divide.click();
        getDigit(4).click();
        equals.click();

        resultText = result.getText();

        Assert.assertEquals("2", resultText);




        driver.closeApp();
    }


    //Let's open Etsy application
    @Test
    public void test2() throws Exception{
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", Platform.ANDROID);
        desiredCapabilities.setCapability(MobileCapabilityType.VERSION, "7.0");
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel_2");
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        desiredCapabilities.setCapability("app", "/data/app/com.etsy.android-1/base.apk");
        driver = new AppiumDriver<>(new URL("http://localhost:4723/wd/hub"), desiredCapabilities);
        Thread.sleep(3000);

        MobileElement searchBox = driver.findElement(MobileBy.id("com.etsy.android:id/search_src_text"));
        Thread.sleep(1000);
        searchBox.sendKeys("Java", Keys.ENTER);
        //driver.getKeyboard().pressKey(Keys.ENTER);

        Thread.sleep(6000);

        driver.closeApp();
    }

    public MobileElement getDigit(int digit){
        MobileElement element = driver.findElement(By.id("com.android.calculator2:id/digit_"+digit));
        return element;
    }

    public static void main(String[] args) {
        System.out.println();
    }


    @Test
    public void android10() throws Exception{
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        //since we use android, put android. could be IOS
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
        //version of your device
        desiredCapabilities.setCapability(MobileCapabilityType.VERSION, "10.0");
        //name of the device
        //if it's a real device, you need to use your parameter
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel_2_API_29_android_10");
        //either you can specify app --> //path/to/the/app.apk
        //or, if app is already installed, you need to specify appActivity and AppPackage
        //this info you can find in the internet, at work - from developers
        //otherwise, you can use apk info app to find this information
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");

        desiredCapabilities.setCapability("app", "/data/app/com.etsy.android-1/base.apk");
        driver = new AppiumDriver<>(new URL("http://localhost:4723/wd/hub"), desiredCapabilities);

        Thread.sleep(3000);

        MobileElement searchBox = driver.findElement(MobileBy.id("com.etsy.android:id/search_src_text"));
        Thread.sleep(1000);
        searchBox.sendKeys("Java", Keys.ENTER);
        //driver.getKeyboard().pressKey(Keys.ENTER);

        Thread.sleep(6000);

        driver.closeApp();
    }


}
