package com.dmytrohont.test.utils;

import com.codeborne.selenide.Selenide;

public class CustomWaiter {

    public static void waitPageReadyStateComplete() {
        Selenide.Wait().until(dr ->
                "complete".equals(Selenide.executeJavaScript("return document.readyState")));
    }
}
