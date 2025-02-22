package com.mobile;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.testng.Assert.assertEquals;

public class IosSafariTest extends AppiumBaseTest {

    private IOSDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setup(Method m) throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(CapabilityType.PLATFORM_NAME, "iOS");
        caps.setCapability(CapabilityType.BROWSER_NAME, "Safari");
        caps.setCapability("automationName", "XCUITest");
        caps.setCapability("udid", "00008120-0006642A14A2201E");
//        caps.setCapability("udid", "DA169D4F-433C-40D1-A81F-12093C7801AA");
        driver = new IOSDriver(new URL("http://127.0.0.1:4723"), caps);
    }

    @Test
    public void bStackDemoLogin() {
        Wait<IOSDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .ignoring(NotFoundException.class);
        driver.get("https://bstackdemo.com");
        wait.until(elementToBeClickable(By.id("signin"))).click();
        wait.until(elementToBeClickable(By.cssSelector("#username .css-19bqh2r"))).click();
        driver.findElement(By.id("react-select-2-option-0-3")).click();
        wait.until(elementToBeClickable(By.cssSelector("#password .css-19bqh2r"))).click();
        driver.findElement(By.id("react-select-3-option-0-0")).click();
        driver.findElement(By.id("login-btn")).click();
        String username = wait.until(presenceOfElementLocated(By.className("username"))).getText();
        assertEquals(username, "fav_user", "Incorrect username");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.get("about:blank");
        driver.quit();
    }

}
