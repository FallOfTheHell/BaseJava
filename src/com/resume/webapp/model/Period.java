package com.resume.webapp.model;

import java.util.Date;
import java.util.Objects;

public class Period {

    private final String title;
    private final String description;
    private final Date startDate;
    private final Date endData;

    public Period(String title, String description, Date startDate, Date endData) {
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endData = endData;
    }

    public String getDescription() {
        return description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndData() {
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
