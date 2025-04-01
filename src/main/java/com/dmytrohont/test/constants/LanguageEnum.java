package com.dmytrohont.test.constants;

public enum LanguageEnum {

    EN("English", "a[hreflang='en']", "About us"),
    FR("Français", "a[hreflang='fr']", "A propos"),
    IT("Italian", "a[hreflang='it']", "Chi siamo");

    private final String languageName;
    private final String locator;
    private final String verificationPhrase;

    LanguageEnum(String languageName, String locator, String checkPhrase) {
        this.languageName = languageName;
        this.locator = locator;
        this.verificationPhrase = checkPhrase;
    }

    public String getLanguageName() {
        return languageName;
    }

    public String getLocator() {
        return locator;
    }

    public String getVerificationPhrase() {
        return verificationPhrase;
    }

}
