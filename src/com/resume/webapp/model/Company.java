package com.resume.webapp.model;

import java.util.List;
import java.util.Objects;

public class Company {

    private final List<Period> periodList;
    private final String name;
    private final String website;

    public Company(List<Period> periodList, String name, String website) {
        this.periodList = periodList;
        this.name = name;
        this.website = website;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Company company1 = (Company) o;
        return Objects.equals(periodList, company1.periodList) && Objects.equals(name, company1.name)
                && Objects.equals(website, company1.website);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), periodList, name, website);
    }


    public List<Period> getPeriodList() {
        return periodList;
    }

    public String getName() {
        return name;
    }

    public String getWebsite() {
        return website;
    }

    @Override
    public String toString() {
        return "Company{" +
                "periodList=" + periodList +
                ", company='" + name + '\'' +
                ", website='" + website + '\'' +
                '}';
    }
}
