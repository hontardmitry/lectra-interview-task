package com.dmytrohont.test;

import com.dmytrohont.test.constants.LanguageEnum;
import com.dmytrohont.test.ui.components.pages.GoogleSearchPage;
import com.dmytrohont.test.ui.components.pages.LectraCareersPage;
import com.dmytrohont.test.ui.components.pages.LectraHomePage;
import org.testng.annotations.Test;

public class LectraTest extends AbstractTest {

    @Test(description = "Test case for interview")
    public void testLectraWebsiteNavigation() {
        GoogleSearchPage googleSearchPage = new GoogleSearchPage();
        googleSearchPage.open();

        LectraHomePage lectraHomePage =
                googleSearchPage.searchForQueryAndNavigateToLectra("Lectra");

        LectraCareersPage lectraCareersPage =
                lectraHomePage.acceptCookies()
                        .switchLanguage(LanguageEnum.EN)
                        .navigateToLectraFashion()
                        .navigateToAutomotive()
                        .navigateToFurniture()
                        .navigateToSubTabOfAboutUs("Discover Lectra")
                        .clickOnViewJobOpeningsLink()
                        .clickOpenOpportunities();

        lectraCareersPage
                .acceptCareerCookies()
                .clickSearchJobsLink()
                .openFirstJob()
                .closeJobTab();
    }
}
