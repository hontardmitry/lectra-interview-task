package com.dmytrohont.test.ui.components.pages;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.closeWindow;
import static com.codeborne.selenide.Selenide.switchTo;

import com.codeborne.selenide.SelenideElement;
import com.dmytrohont.test.ui.components.AbstractComponent;

public abstract class AbstractPage extends AbstractComponent {

    protected final SelenideElement pageBody = $("body");

    public void closeJobTab() {
        closeWindow();
        switchTo().window(0);
    }


}
