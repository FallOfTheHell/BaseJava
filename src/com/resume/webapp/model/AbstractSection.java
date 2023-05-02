package com.resume.webapp.model;

import java.util.Objects;

public abstract class AbstractSection extends Resume {

    private final SectionType sectionType;

    public AbstractSection(String fullName, SectionType sectionType) {
        super(fullName);
        this.sectionType = sectionType;
    }

    public SectionType getSectionType() {
        return sectionType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AbstractSection that = (AbstractSection) o;
        return sectionType == that.sectionType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), sectionType);
    }

    @Override
    public String toString() {
        return "AbstractSection{" +
                "sectionType=" + sectionType +
                '}';
    }
}
