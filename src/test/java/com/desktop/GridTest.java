package com.desktop;

import org.openqa.selenium.By;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.testng.Assert.assertEquals;

public class GridTest {

    private static final String USERNAME = System.getenv("BROWSERSTACK_USERNAME");
    private static final String AUTOMATE_KEY = System.getenv("BROWSERSTACK_ACCESS_KEY");
//    private static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@y1kc8bkb-hub.browserstack-ats.com/wd/hub";
    private static final String URL = "https://y1kc8bkb-hub.browserstack-ats.com/wd/hub";
//    private static final String URL = "https://jg7oosrx-hub.browserstack-ats.com/wd/hub";

    private WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setup(Method m) throws MalformedURLException {
        MutableCapabilities caps = new MutableCapabilities();
        caps.setCapability("browserName", "chrome");
        Map<String, Object> bstackOptions = new HashMap<>();
//        caps.setCapability("browserVersion", "latest");
        bstackOptions.put("userName", USERNAME);
        bstackOptions.put("accessKey", AUTOMATE_KEY);
//        bstackOptions.put("userName", "anselfrancisdsou_qOz92H");
//        bstackOptions.put("accessKey", "Dhw4vBXknBdRCqkSK1iQ");
        bstackOptions.put("projectName", "Turbo Scale Java Project");
        bstackOptions.put("buildName", "Turbo Scale Java Build");
        bstackOptions.put("sessionName", m.getName());
        caps.setCapability("bstack:options", bstackOptions);
        URL remoteURL = new URL(URL);
        driver = new RemoteWebDriver(remoteURL, caps);
    }

    @Test
    public void bStackDemoLogin() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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
        driver.quit();
    }

}
