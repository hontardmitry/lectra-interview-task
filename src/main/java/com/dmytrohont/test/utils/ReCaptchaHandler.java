package com.dmytrohont.test.utils;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class ReCaptchaHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReCaptchaHandler.class);

    private ReCaptchaHandler() {
    }

    public static void handleGoogleReCaptchaIfAppears() {
        var timeout = Duration.ofSeconds(1);
        var wait = new WebDriverWait(WebDriverRunner.getWebDriver(), timeout);
        try {
            wait.until(ExpectedConditions
                    .frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@title='reCAPTCHA']")));
            var captchaCheckbox = wait.until(ExpectedConditions
                    .elementToBeClickable((By.xpath("//div[@class='recaptcha-checkbox-border']"))));
            captchaCheckbox.click();
        } catch (TimeoutException te) {
            LOGGER.info("No reCAPTCHA appeared on the page during {} seconds", timeout.toSeconds());
        }
    }
}
