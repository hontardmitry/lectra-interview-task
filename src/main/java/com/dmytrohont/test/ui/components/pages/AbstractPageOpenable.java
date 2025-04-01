package com.dmytrohont.test.ui.components.pages;

import com.codeborne.selenide.Selenide;

public abstract class AbstractPageOpenable<T extends AbstractPageOpenable<T>>
        extends AbstractPage
        implements Openable<T> {

    protected final String pageUrl;

    protected AbstractPageOpenable() {
        this.pageUrl = getPageUrl();
    }

    @Override
    @SuppressWarnings("unchecked")
    public T open() {
        Selenide.open(pageUrl);
        return (T) this;
    }

    public abstract String getPageUrl();
}
