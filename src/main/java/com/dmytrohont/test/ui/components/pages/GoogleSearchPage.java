package com.dmytrohont.test.ui.components.pages;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;
import static com.dmytrohont.test.utils.CustomWaiter.waitPageReadyStateComplete;
import static com.dmytrohont.test.utils.ReCaptchaHandler.handleGoogleReCaptchaIfAppears;

import com.codeborne.selenide.SelenideElement;

import java.util.Locale;

public class GoogleSearchPage extends AbstractPageOpenable<GoogleSearchPage> {

    private static final String GOOGLE_HOME = "https://www.google.com";
    private static final String LINK_TO_CLICK_TEMPLATE = "a[href*='%s']";
    private final SelenideElement searchBox = $("[name='q']");
    private final SelenideElement acceptCookiesButton = $("#L2AGLb");

    public GoogleSearchPage() {
        super();
    }

    @Override
    public String getPageUrl() {
        return GOOGLE_HOME;
    }

    public void searchForQuery(String query) {
        acceptCookiesButton.click();
        waitPageReadyStateComplete();
        searchBox.should(enabled).setValue(query).pressEnter();

        handleGoogleReCaptchaIfAppears();
    }

    public LectraHomePage searchForQueryAndNavigateToLectra(String query) {
        searchForQuery(query);
        waitPageReadyStateComplete();
        getFirstSearchResultLink(query).click();

        logger.info("Search is performed for query '{}'", query);
        return page(LectraHomePage.class);
    }

    private SelenideElement getFirstSearchResultLink(String query) {
        return  $(String.format(LINK_TO_CLICK_TEMPLATE, query.toLowerCase(Locale.ROOT)));
    }
}
