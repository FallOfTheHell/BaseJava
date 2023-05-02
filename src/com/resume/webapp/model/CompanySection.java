package com.resume.webapp.model;

import java.util.List;
import java.util.Objects;

public class CompanySection extends AbstractSection {

    private final List<Company> companyList;

    public CompanySection(String fullName, SectionType sectionType, List<Company> companyList) {
        super(fullName, sectionType);
        this.companyList = companyList;
    }


    public List<Company> getCompanyList() {
        return companyList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CompanySection that = (CompanySection) o;
        return Objects.equals(companyList, that.companyList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), companyList);
    }

    @Override
    public String toString() {
        return "CompanySection{" +
                "companyList=" + companyList +
                '}';
    }
}
