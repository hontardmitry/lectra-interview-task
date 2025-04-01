package com.dmytrohont.test.ui.components.pages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.dmytrohont.test.utils.CustomWaiter.waitPageReadyStateComplete;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.interactions.Actions;

public class LectraCareersPage extends AbstractPage {

    private final ElementsCollection searchJobsButtons = $$("input[value='Search jobs']");
    private final SelenideElement firstJobListing = $("td a.jobTitle-link");

    public LectraCareersPage acceptCareerCookies() {
        waitPageReadyStateComplete();

        Actions act = new Actions(WebDriverRunner.getWebDriver());
        act.moveToLocation(1400, 800).click();
        return this;
    }

    public LectraCareersPage clickSearchJobsLink() {
        searchJobsButtons.findBy(visible).click();
        return this;
    }

    public LectraCareersPage openFirstJob() {
        firstJobListing.click();
        return this;
    }
}
