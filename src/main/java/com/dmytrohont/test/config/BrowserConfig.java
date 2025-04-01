package com.dmytrohont.test.config;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeOptions;

public class BrowserConfig {

    public static void setupDefaultBrowserConfig() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.setPageLoadStrategy(PageLoadStrategy.NONE);

        Configuration.browserCapabilities = options;
        Configuration.browserSize = null;
        Configuration.browser = "chrome";
        Configuration.timeout = 30000;
    }
}
