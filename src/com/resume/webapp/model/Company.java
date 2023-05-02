package com.resume.webapp.model;

import java.util.List;
import java.util.Objects;

public class Company {

    private final List<Period> periodList;
    private final String company;
    private final String website;

    public Company(List<Period> periodList, String company, String website) {
        this.periodList = periodList;
        this.company = company;
        this.website = website;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Company company1 = (Company) o;
        return Objects.equals(periodList, company1.periodList) && Objects.equals(company, company1.company)
                && Objects.equals(website, company1.website);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), periodList, company, website);
    }


    public List<Period> getPeriodList() {
        return periodList;
    }

    public String getCompany() {
        return company;
    }

    public String getWebsite() {
        return website;
    }

    @Override
    public String toString() {
        return "Company{" +
                "periodList=" + periodList +
                ", company='" + company + '\'' +
                ", website='" + website + '\'' +
                '}';
    }
}
