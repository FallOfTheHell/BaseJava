package com.resume.webapp.model;

import java.util.List;
import java.util.Objects;

public class ContactType extends Resume{
    private final Integer numberPhone;
    private final String skype;
    private final String mail;
    private final List<String> usefulLinks;

    public ContactType(String uuid, String fillName, Integer numberPhone,
                       String skype, String mail, List<String> usefulLinks) {
        super(uuid, fillName);
        this.numberPhone = numberPhone;
        this.skype = skype;
        this.mail = mail;
        this.usefulLinks = usefulLinks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactType that = (ContactType) o;
        return Objects.equals(numberPhone, that.numberPhone) && Objects.equals(skype, that.skype)
                && Objects.equals(mail, that.mail) && Objects.equals(usefulLinks, that.usefulLinks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberPhone, skype, mail, usefulLinks);
    }

    public Integer getNumberPhone() {
        return numberPhone;
    }

    public String getSkype() {
        return skype;
    }

    public String getMail() {
        return mail;
    }

    public List<String> getUsefulLinks() {
        return usefulLinks;
    }
}
