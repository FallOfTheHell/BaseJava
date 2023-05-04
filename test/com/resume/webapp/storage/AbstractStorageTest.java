package com.resume.webapp.storage;

import com.resume.webapp.exception.ExistStorageException;
import com.resume.webapp.exception.NotExistStorageException;
import com.resume.webapp.exception.StorageException;
import com.resume.webapp.model.Resume;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractStorageTest {

    protected final Storage storage;

    protected static final String UUID_NOT_EXIST = "uuid_not_exist";

    protected static final String UUID_1 = "uuid_1";
    protected static final Resume resume1 = new Resume(UUID_1, UUID_1, contacts);
    protected static final String UUID_2 = "uuid_2";
    protected static final Resume resume2 = new Resume(UUID_2, UUID_2, contacts);
    protected static final String UUID_3 = "uuid_3";
    protected static final Resume resume3 = new Resume(UUID_3, UUID_3, contacts);
    protected static final String UUID_4 = "uuid_4";
    protected static final Resume resume4 = new Resume(UUID_4, UUID_4, contacts);

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
        assertGet(resume1);
        assertGet(resume2);
        assertGet(resume3);
    }

    @Test
    void save() {
        storage.save(resume4);
        assertGet(resume4);
        assertSize(4);
    }

    public void assertSize(int size){
        assertEquals(size, storage.size());
    }

    @Test
    void update() {
        Resume resume = new Resume(UUID_1, UUID_1, contacts);
        storage.update(resume);
        assertSame(resume, storage.get(UUID_1));
    }

    @Test
    void delete() {
        storage.delete(UUID_3);
        assertThrows(NotExistStorageException.class, () -> {
            storage.get(resume3.getUuid());
        });
        assertSize(2);
    }

    @Test
    void getAllSorted() {
        final List<Resume> actual = List.of(resume1, resume2, resume3);
        assertEquals(actual, storage.getAllSorted());
    }

    @Test
    void clear() {
        storage.clear();
        assertEquals(List.of(), storage.getAllSorted());
        assertSize(0);
    }

    @Test
    void getNotExist() {
        assertThrows(NotExistStorageException.class, () -> {
            storage.get(UUID_NOT_EXIST);
        });
    }

    @Test
    void saveExist(){
        assertThrows(StorageException.class, () -> {
            storage.save(resume1);
        });

        assertThrows(ExistStorageException.class, () -> {
            storage.save(resume1);
        });
    }

    @Test
    void updateNotExist(){
        assertThrows(NotExistStorageException.class, () -> {
            storage.update(resume4);
        });
    }

    @Test
    void deleteNotExist(){
        assertThrows(NotExistStorageException.class, () -> {
            storage.delete(UUID_NOT_EXIST);
        });
    }

    void assertGet(Resume resume){
        assertEquals(resume, storage.get(resume.getUuid()));
    }
}