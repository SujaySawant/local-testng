package com.mobile;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;

public class AppiumBaseTest {

    private Process appiumProcess;

    @BeforeSuite(alwaysRun = true)
    public void setupAppium() throws IOException, InterruptedException {
        String[] args = new String[]{"appium"};
//        String[] args = new String[]{"appium", "--allow-insecure", "chromedriver_autodownload"};
        appiumProcess = new ProcessBuilder(args).start();
        Thread.sleep(5000);
    }

    @AfterSuite(alwaysRun = true)
    public void terminateAppium() {
        appiumProcess.destroy();
    }

}
