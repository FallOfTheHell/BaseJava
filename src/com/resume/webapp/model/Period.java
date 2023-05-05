package com.resume.webapp.model;

import java.time.LocalDate;
import java.util.Objects;

public class Period {

    private final String title;
    private final String description;
    private final LocalDate startDate;
    private final LocalDate endDate;

    public Period(String title, String description, LocalDate startDate, LocalDate endDate) {
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
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
                && Objects.equals(startDate, period.startDate) && Objects.equals(endDate, period.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), description, title, startDate, endDate);
    }

    @Override
    public String toString() {
        return  "Описание: " + description +
                "; Заголовок: " + title +
                "; Начальная дата: " + startDate +
                "; Конечная дата: " + endDate + ";";
    }
}
