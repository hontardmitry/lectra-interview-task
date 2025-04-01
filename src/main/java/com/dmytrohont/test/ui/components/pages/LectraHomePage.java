package com.dmytrohont.test.ui.components.pages;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.page;
import static com.dmytrohont.test.constants.TemplateAndConstant.NAVIGATED_TO_SUB_TAB;
import static com.dmytrohont.test.constants.TemplateAndConstant.NAVIGATED_TO_TAB;
import static java.util.Map.entry;

import com.codeborne.selenide.SelenideElement;
import com.dmytrohont.test.constants.LanguageEnum;

import java.util.Map;

public class LectraHomePage extends AbstractPage {

    public static final String ABOUT_US = "About us";
    public static final String FASHION = "Fashion";
    public static final String AUTOMOTIVE = "Automotive";
    public static final String FURNITURE = "Furniture";
    public static final String LECTRA_AND_FASHION = "Lectra & Fashion";

    public static final Map<String, Integer> MAIN_MENU_TABS = Map.ofEntries(
            entry(FASHION, 0),
            entry(AUTOMOTIVE, 1),
            entry(FURNITURE, 2)
    );
    public static final Map<String, Integer> FASHION_MENU_SUB_TABS = Map.ofEntries(
            entry(LECTRA_AND_FASHION, 0)
    );

    public static final Map<String, Integer> SECONDARY_MENU_TAB_NUMBERS = Map.ofEntries(
            entry(ABOUT_US, 0)
    );
    public static final Map<String, Integer> ABOUT_US_MENU_TAB_NUMBERS = Map.ofEntries(
            entry("Discover Lectra", 0)
    );

    private static final String LECTRA_FASHION_CHECK_PHRASE = "Ensure top-line growth";

    private final SelenideElement acceptCookiesButton = $("button#ppms_cm_agree-to-all");
    private final SelenideElement languageSwitcher = $("#block-lectra-b5-languageswitcherinterfacetext-2");
    private final SelenideElement languageSwitcherSubMenu =
            languageSwitcher.$(".submenu-language");

    private final SelenideElement mainMenu = $("#block-lectra-b5-mainnavigation");
    private final SelenideElement secondaryNavigationMenu = $("#block-lectra-b5-headersecondarynavigation-2");
    private final SelenideElement secondaryLinkContainerMenuWrapper =
            $(".secondaryMenu_link_content_wrapper");

    private final SelenideElement discoverAutomotiveLink = $("a[href*='automotive']");
    private final SelenideElement discoverFurnitureLink = $("a[href*='furniture']");
    private final SelenideElement viewJobOpeningsLink =
            $x("//h3[text()='Work at Lectra']/ancestor::div[@class='content']" +
                    "//a[contains(@href, 'work-at-lectra')]");
    private final SelenideElement ourJobOpportunitiesLink = $("a[href*='careers.lectra.com']");

    public LectraHomePage() {
        super();
    }


    public LectraHomePage acceptCookies() {
        acceptCookiesButton.click();
        return this;
    }

    private SelenideElement getLanguageElement(LanguageEnum language) {
        return $(language.getLocator());
    }

    private SelenideElement getMainMenuOption(String option) {
        return getTabByNumber(MAIN_MENU_TABS.get(option), getListItems(mainMenu));
    }

    private SelenideElement getMainMenuSubOption(String option, String subOption) {
        return getTabByNumber(FASHION_MENU_SUB_TABS.get(subOption), getListItems(getMainMenuOption(option)));

    }

    public LectraHomePage switchLanguage(LanguageEnum language) {
        var expectedLanguage = language.getLanguageName();
        var languageElement = getLanguageElement(language);

        secondaryNavigationMenu.should(visible);
        if (!secondaryNavigationMenu.getText().contains(language.getVerificationPhrase())) {
            openContainerIfNotDisplayed(languageSwitcherSubMenu, languageSwitcher);
            languageElement.click();
            languageSwitcher.should(visible);
            logger.info("Page language is '{}'", expectedLanguage);
        } else {
            logger.info("Language '{}' is already selected.", expectedLanguage);
        }

        return this;
    }

    public LectraHomePage navigateToLectraFashion() {
        getMainMenuOption(FASHION).click();
        getMainMenuSubOption(FASHION, LECTRA_AND_FASHION).click();
        pageBody.shouldHave(text(LECTRA_FASHION_CHECK_PHRASE));

        logger.info(NAVIGATED_TO_SUB_TAB, FASHION, LECTRA_AND_FASHION);
        return this;
    }

    public LectraHomePage navigateToTabAndCheck(String tabName, SelenideElement tabIdentifier) {
        getMainMenuOption(tabName).should(visible).click();
        tabIdentifier.shouldBe(visible);

        logger.info(NAVIGATED_TO_TAB, tabName);
        return this;
    }

    public LectraHomePage navigateToAutomotive() {
        return navigateToTabAndCheck(AUTOMOTIVE, discoverAutomotiveLink);
    }

    public LectraHomePage navigateToFurniture() {
        return navigateToTabAndCheck(FURNITURE, discoverFurnitureLink);
    }


    public LectraHomePage navigateToSubTabOfAboutUs(String subTab) {
        var tabElement =
                getTabByNumber(SECONDARY_MENU_TAB_NUMBERS.get(ABOUT_US), getListItems(secondaryNavigationMenu));

        openContainerIfNotDisplayed(secondaryLinkContainerMenuWrapper, tabElement);
        getTabByNumber(ABOUT_US_MENU_TAB_NUMBERS.get(subTab), getListItems(secondaryNavigationMenu)).$("a")
                .click();

        logger.info(NAVIGATED_TO_SUB_TAB, ABOUT_US, subTab);
        return this;
    }

    public LectraHomePage clickOnViewJobOpeningsLink() {
        scrollByPixels(12000);
        scrollToElement(viewJobOpeningsLink);
        viewJobOpeningsLink.click();
        logger.info("viewJobOpeningsLink was clicked");

        return this;
    }

    public LectraCareersPage clickOpenOpportunities() {
        scrollByPixels(1000);
        scrollToElement(ourJobOpportunitiesLink).click();
        logger.info("ourJobOpportunitiesLink was clicked");

        return page(LectraCareersPage.class);
    }

}