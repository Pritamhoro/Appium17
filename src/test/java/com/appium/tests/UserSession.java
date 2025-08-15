package com.appium.tests;

import com.appium.base.Base;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.time.Duration;

public class UserSession extends Base {
    @Test
    public void clicksOnMedia(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"Media\"]")).click();
    }
}
