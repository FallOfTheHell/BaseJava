package com.resume.webapp.model;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.Objects;

public class Period extends Company {

    private final String description;
    private final String title;
    private final Data startDate;
    private final Data endData;

    public Period(String fullName, SectionType sectionType, List<Company> companyList,
                  List<Period> periodList, String company, String website, String description,
                  String title, Data startDate, Data endData) {
        super(fullName, sectionType, companyList, periodList, company, website);
        this.description = description;
        this.title = title;
        this.startDate = startDate;
        this.endData = endData;
    }


    public String getDescription() {
        return description;
    }

    public Data getStartDate() {
        return startDate;
    }

    public Data getEndData() {
        return endData;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Period period = (Period) o;
        return Objects.equals(description, period.description) && Objects.equals(title, period.title)
                && Objects.equals(startDate, period.startDate) && Objects.equals(endData, period.endData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), description, title, startDate, endData);
    }

    @Override
    public String toString() {
        return "Period{" +
                "description='" + description + '\'' +
                ", title='" + title + '\'' +
                ", startDate=" + startDate +
                ", endData=" + endData +
                '}';
    }
}
