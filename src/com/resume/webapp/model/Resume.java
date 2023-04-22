package com.resume.webapp.model;


import java.util.Objects;

public class Resume implements Comparable<Resume> {

//    public static class SortResume implements Comparator<Resume> {
//
//        @Override
//        public int compare(Resume o1, Resume o2) {
//            return o1.getFillName().compareTo(o2.getFillName());
//        }
//    }

    private final String uuid;

    private final String fillName;

//    public Resume(String uuid) {
//        this.uuid = uuid;
//    }

    public Resume(String uuid, String fillName) {
        this.uuid = uuid;
        this.fillName = fillName;
    }

    public String getUuid() {
        return uuid;
    }

    public String getFillName() {
        return fillName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return Objects.equals(uuid, resume.uuid) && Objects.equals(fillName, resume.fillName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, fillName);
    }

    @Override
    public String toString() {
        return "uuid: " + uuid + ", FillName: " + fillName;
    }

    @Override
    public int compareTo(Resume o) {
        int result = this.fillName.compareTo(o.fillName);
        if (result == 0) {
            result = this.uuid.compareTo(o.uuid);
        }
        return result;
    }
}