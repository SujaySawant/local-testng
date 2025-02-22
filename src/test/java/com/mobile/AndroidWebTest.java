package com.mobile;

import io.appium.java_client.android.AndroidDriver;
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

public class AndroidWebTest extends AppiumBaseTest {

    private AndroidDriver driver;
    private Wait<AndroidDriver> wait;

    @BeforeMethod(alwaysRun = true)
    public void setup(Method m) throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(CapabilityType.PLATFORM_NAME, "Android");
        caps.setCapability(CapabilityType.BROWSER_NAME, "Chrome");
        caps.setCapability("automationName", "UIAutomator2");
        caps.setCapability("udid̵", "emulator-5556");
//        caps.setCapability("appPackage", "com.android.chrome");
//        caps.setCapability("appActivity", "com.google.android.apps.chrome.Main");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), caps);
        wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(10)).ignoring(NotFoundException.class);
//        wait.until(d -> d.getContextHandles().contains("WEBVIEW_chrome"));
//        driver.context("WEBVIEW_chrome");
    }

    @Test
    public void bStackDemoLogin() {
        driver.get("https://bstackdemo.com");
        wait.until(elementToBeClickable(By.id("signin"))).click();
        wait.until(elementToBeClickable(By.cssSelector("#username .css-19bqh2r"))).click();
        driver.findElement(By.id("react-select-2-option-0-3")).click();
        driver.findElement(By.cssSelector("#password .css-19bqh2r")).click();
        driver.findElement(By.id("react-select-3-option-0-0")).click();
        driver.findElement(By.id("login-btn")).click();
        String username = wait.until(presenceOfElementLocated(By.className("username"))).getText();
        assertEquals(username, "fav_user", "Incorrect username");
    }

    @AfterMethod(alwaysRun = true)
    public void closeDriver() {
        driver.get("about:blank");
        driver.quit();
    }

}
