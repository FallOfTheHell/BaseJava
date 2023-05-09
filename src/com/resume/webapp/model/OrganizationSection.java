package com.resume.webapp.model;

import java.util.List;
import java.util.Objects;

public class OrganizationSection extends AbstractSection {

    private final List<Organization> companies;

    public OrganizationSection(List<Organization> companies) {
        this.companies = companies;
    }

    public List<Organization> getCompanies() {
        return companies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        OrganizationSection that = (OrganizationSection) o;
        return Objects.equals(companies, that.companies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), companies);
    }

    @Override
    public String toString() {
        return "Список компаний: " + companies + ";";
    }
}
