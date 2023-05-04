package com.resume.webapp.model;


import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class Resume implements Comparable<Resume> {

    private final String uuid;

    private final String fullName;

    private final EnumMap<ContactType, String> contacts;

    private final EnumMap<SectionType, AbstractSection> sections;

    //TODO: Мне нужно было contacts и sections добавлять в 2 конструктора в качестве аргумента?
    // Если да, то все остальное что писали, отвалится.
    // Чтобы все не отвалилось я сделал так, это будет правильно или нет?
    public Resume(String fullName) {
        this(fullName, UUID.randomUUID().toString());
    }

    public Resume(String uuid, String fullName) {
        Objects.requireNonNull(uuid, "uuid must not be null");
        Objects.requireNonNull(fullName, "fullName must not be null");
        this.contacts = new EnumMap<>(ContactType.class);
        this.sections = new EnumMap<>(SectionType.class);
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    public Map<ContactType, String> getContacts() {
        return contacts;
    }

    public Map<SectionType, AbstractSection> getSections() {
        return sections;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return Objects.equals(uuid, resume.uuid) && Objects.equals(fullName, resume.fullName)
                && Objects.equals(contacts, resume.contacts) && Objects.equals(sections, resume.sections);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, fullName, contacts, sections);
    }

    @Override
    public String toString() {
        return "uuid-" + uuid + ", FillName: " + fullName;
    }

    @Override
    public int compareTo(Resume o) {
        int result = this.fullName.compareTo(o.fullName);
        return result != 0 ? result : this.uuid.compareTo(o.uuid);
    }
}