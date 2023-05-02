package com.resume.webapp.model;

import java.util.List;
import java.util.Objects;

public class ListSection extends AbstractSection {

    private final List<String> stringList;

    public ListSection(String fullName, SectionType sectionType, List<String> stringList) {
        super(fullName, sectionType);
        this.stringList = stringList;
    }


    public List<String> getStringList() {
        return stringList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ListSection that = (ListSection) o;
        return Objects.equals(stringList, that.stringList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), stringList);
    }

    @Override
    public String toString() {
        return "ListSection{" +
                "stringList=" + stringList +
                '}';
    }
}
