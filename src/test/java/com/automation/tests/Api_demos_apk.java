package com.automation.tests;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.offset.ElementOption;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.net.MalformedURLException;
import java.net.URL;

public class Api_demos_apk {

    private AndroidDriver<MobileElement> driver;

    @Before
    public void setup(){
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel_2");
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
        caps.setCapability(MobileCapabilityType.VERSION, "7.0");
        caps.setCapability(MobileCapabilityType.APP,System.getProperty("user.dir")+"/api_demos.apk");
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");

        try{
            driver = new AndroidDriver<MobileElement>(new URL("http://localhost:4723/wd/hub"), caps);
        }catch (MalformedURLException e){
            e.printStackTrace();
        }
    }

    @After
    public void tearDown(){
        driver.closeApp();
    }

    @Test
    public void api_demos_apk_test() throws Exception{
        WebDriverWait wait = new WebDriverWait(driver, 20);


        wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("Views")));
        WebElement viewButton = driver.findElement(MobileBy.AccessibilityId("Views"));

        viewButton.click();

        //in order to read elements it needs to be visible on the page, make it visible by scrolling down or up
        //check uiautomator web page documentation
        MobileElement webView = driver.findElement(MobileBy.AndroidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true))" +
                                                ".scrollIntoView(new UiSelector().textContains(\"WebView\"));"
                                ));
        webView.click();
        Thread.sleep(2000);

        //go back
        driver.navigate().back();

        //find element by scrolling the page
        MobileElement imageSwitcher = driver.findElement(MobileBy.AndroidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true))" +
                                ".scrollIntoView(new UiSelector().textContains(\"ImageSwitcher\"));"
                            ));

        imageSwitcher.click();
        driver.navigate().back();

        //if we need to tap on element or use long press, we can use TouchAction class
        TouchAction touchAction = new TouchAction(driver);

        //to tap on element
        //you can chain actions like in Actions class
        //so you can tap twice, drag and  drop (long press followed by moveTo target).build().perform();
        touchAction.tap(new ElementOption().withElement(imageSwitcher)).perform();


        //to use keyboard keys
        int i=0;
        while (i++<8){
            Thread.sleep(1500);
            driver.getKeyboard().pressKey(Keys.ARROW_RIGHT);
        }

        Thread.sleep(1000);
    }


}
