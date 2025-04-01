package com.dmytrohont.test;

import static com.dmytrohont.test.config.BrowserConfig.setupDefaultBrowserConfig;

import org.testng.annotations.BeforeClass;

public abstract class AbstractTest {

    @BeforeClass
    public void setup() {
        setupDefaultBrowserConfig();
    }
}
