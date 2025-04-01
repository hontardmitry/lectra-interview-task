package com.dmytrohont.test.ui.components;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Condition.exist;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Arrays;

public abstract class AbstractComponent {

    private static final int SCROLL_TIMEOUT = 60;
    private static final String IS_ACTIVE_INDICATOR = "is-active";
    protected final Logger logger;

    protected AbstractComponent() {
        logger = LoggerFactory.getLogger(this.getClass());
    }

    public boolean isPresentVisibleEnabled(SelenideElement elementToCheck) {
        return elementToCheck.exists()
                && elementToCheck.isDisplayed()
                && elementToCheck.isEnabled();
    }

    public void openContainerIfNotDisplayed(SelenideElement container, SelenideElement containerTrigger) {
        if (!isPresentVisibleEnabled(container)) {
            containerTrigger.click();
        }
    }

    public boolean elementHasClass(SelenideElement element, String searchedClass) {
        var cssClass = element.getAttribute("class");

        assert cssClass != null;
        return Arrays.asList(cssClass.split(" ")).contains(searchedClass);
    }

    public SelenideElement getTabByNumber(int tabNumber, ElementsCollection tabs) {
        tabs.shouldHave(sizeGreaterThanOrEqual(tabNumber + 1));
        return tabs.get(tabNumber);
    }

    public ElementsCollection getListItems(SelenideElement container) {
        return container.$("ul").$$x("./li");
    }

    public SelenideElement scrollToElement(SelenideElement element) {
        element.should(exist);
        var driver = WebDriverRunner.getWebDriver();
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        new WebDriverWait(driver, Duration.ofSeconds(SCROLL_TIMEOUT))
                .until(ExpectedConditions.elementToBeClickable(element));

        return element;
    }

    public void scrollByPixels(int pixelsToScroll) {
        var driver = WebDriverRunner.getWebDriver();
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,arguments[0])", pixelsToScroll);
    }
}
