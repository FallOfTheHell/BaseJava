package com.resume.webapp.model;

public enum ContactType {

    PHONE_NUMBER("Номер телефона"),
    SKYPE("Skype"),
    EMAIL("Email"),
    USEFUL_LINKS("Полезные ссылки");

    private final String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
