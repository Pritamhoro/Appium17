package com.appium.base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import java.net.MalformedURLException;
import java.net.URL;

public class Base {

    private AppiumDriverLocalService appiumService;
    public static AppiumDriver driver;
    protected URL serverURL;

    @BeforeSuite(alwaysRun = true)
    public void startAppiumServer() {
        appiumService = new AppiumServiceBuilder()
                .withIPAddress("127.0.0.1")
                .usingPort(4723)
                .build();
        appiumService.start();
    }

    @BeforeClass(alwaysRun = true)
    public void launchDevice() {
        try {
            serverURL = new URL("http://127.0.0.1:4723");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        UiAutomator2Options options = new UiAutomator2Options();
        options.setAutomationName("UiAutomator2")
                .setApp("C:\\Users\\prita\\IdeaProjects\\MobileAutomation\\src\\main\\resources\\ApiDemos-debug.apk")
                .setDeviceName("Pixel 9 Pro");

        driver = new AndroidDriver(serverURL, options);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterSuite(alwaysRun = true)
    public void stopAppiumServer() {
        if (appiumService != null && appiumService.isRunning()) {
            appiumService.stop();
        }
    }
}
