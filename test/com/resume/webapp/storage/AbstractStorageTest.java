package com.resume.webapp.storage;

import com.resume.webapp.model.Resume;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AbstractStorageTest {

    private final Storage storage;

    private static final String UUID_NOT_EXIST = "uuid_not_exist";

    private static final String UUID_1 = "uuid_1";
    private static final Resume resume1 = new Resume(UUID_1);

    private static final String UUID_2 = "uuid_2";
    private static final Resume resume2 = new Resume(UUID_2);

    private static final String UUID_3 = "uuid_3";
    private static final Resume resume3 = new Resume(UUID_3);

    private static final String UUID_4 = "uuid_4";
    private static final Resume resume4 = new Resume(UUID_4);

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    public void setUp() {
        storage.clear();
        storage.save(resume1);
        storage.save(resume2);
        storage.save(resume3);
    }

    @Test
    void get() {
    }

    @Test
    void save() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}