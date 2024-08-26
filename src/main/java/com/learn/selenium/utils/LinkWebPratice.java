package com.learn.selenium.utils;

public enum LinkWebPratice {
    DROPDOWNS("https://rahulshettyacademy.com/dropdownsPractise"),
    LOCATORS("https://rahulshettyacademy.com/locatorspractice"),
    SELENIUM("https://rahulshettyacademy.com/seleniumPractise"),
    SENT_BE("https://autofx-fe-dev.sentbe.net/login"),
    ASSERTIONS("https://www.spicejet.com/"),

    ;

    private final String value;

    LinkWebPratice(String value) {
        this.value = value;
    }

    public static LinkWebPratice getByValue(String value) {
        for (LinkWebPratice type : values()) {
            if (type.value.equals(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("No enum constant found with value: " + value);
    }

    public String getUrlLink() {
        return value;
    }
}
