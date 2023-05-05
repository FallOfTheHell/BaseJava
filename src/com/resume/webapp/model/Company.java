package com.resume.webapp.model;

import java.util.List;
import java.util.Objects;

public class Company {

    private final List<Period> periods;
    private final String name;
    private final String website;

    public Company(List<Period> periods, String name, String website) {
        this.periods = periods;
        this.name = name;
        this.website = website;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Company company1 = (Company) o;
        return Objects.equals(periods, company1.periods) && Objects.equals(name, company1.name)
                && Objects.equals(website, company1.website);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), periods, name, website);
    }


    public List<Period> getPeriods() {
        return periods;
    }

    public String getName() {
        return name;
    }

    public String getWebsite() {
        return website;
    }

    @Override
    public String toString() {
        return  "Период работы: " + periods +
                "; Компания: " + name +
                "; Сайт: " + website + ';';
    }
}
