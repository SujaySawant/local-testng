package com.mobile;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

import static org.testng.Assert.assertEquals;

public class IosTest extends AppiumBaseTest {

    private IOSDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setup(Method m) throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(CapabilityType.PLATFORM_NAME, "iOS");
        caps.setCapability("automationName", "XCUITest");
        caps.setCapability("udid", "00008120-0006642A14A2201E");
        caps.setCapability("bundleId", "com.sujay.IntegrationApp");
        driver = new IOSDriver(new URL("http://127.0.0.1:4723"), caps);
    }

    @Test
    public void printText() {
        driver.findElement(AppiumBy.id("Text")).click();
        driver.findElement(AppiumBy.iOSNsPredicateString("value == \"Enter a text\"")).sendKeys("Welcome to BrowserStack" + Keys.ENTER);
        assertEquals(driver.findElement(AppiumBy.id("Welcome to BrowserStack")).getText(),
                "Welcome to BrowserStack", "Incorrect text");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }

}
