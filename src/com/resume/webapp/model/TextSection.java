package com.resume.webapp.model;

import java.util.Objects;

public class TextSection extends AbstractSection {

    private final String text;

    public TextSection(String fullName, SectionType sectionType, String text) {
        super(fullName, sectionType);
        this.text = text;
    }


    public String getText() {
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TextSection that = (TextSection) o;
        return Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), text);
    }

    @Override
    public String toString() {
        return "TextSection{" +
                "text='" + text + '\'' +
                '}';
    }
}
