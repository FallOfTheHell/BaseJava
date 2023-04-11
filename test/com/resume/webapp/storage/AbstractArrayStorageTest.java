package com.resume.webapp.storage;

import com.resume.webapp.exception.ExistStorageException;
import com.resume.webapp.exception.NotExistStorageException;
import com.resume.webapp.exception.StorageException;
import com.resume.webapp.model.Resume;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractArrayStorageTest {
    private final Storage storage;

    private static final String UUID_OVERFLOW = "uuid_overflow";
    private static final String UUID_NOT_EXIST = "uuid_not_exist";

    private static final String UUID_1 = "uuid_1";
    private static final Resume resume1 = new Resume(UUID_1);

    private static final String UUID_2 = "uuid_2";
    private static final Resume resume2 = new Resume(UUID_2);

    private static final String UUID_3 = "uuid_3";
    private static final Resume resume3 = new Resume(UUID_3);

    private static final String UUID_4 = "uuid_4";
    private static final Resume resume4 = new Resume(UUID_4);

    public AbstractArrayStorageTest(Storage storage) {
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

    void assertGet(Resume resume){
        assertEquals(resume, storage.get(resume.getUuid()));
    }

    @Test
    void save() {
        storage.save(resume4);
        assertGet(resume4);
        assertSize(4);
    }

    @Test
    void update() {
        Resume resume = new Resume(UUID_1);
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
    void clear() {
        storage.clear();
        assertArrayEquals(new Resume[0], storage.getAll());
        assertSize(0);
    }

    @Test
    void getAll() {
        final Resume[] actual = new Resume[]{resume1, resume2, resume3};
        assertArrayEquals(actual, storage.getAll());
    }

    @Test
    void size() {
        assertSize(3);
    }

    void assertSize(int size){
        assertEquals(size, storage.size());
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

    @Test
    void saveOverflow(){
        try {
            for (int i = 4; i <= 9_000; i++) {
                storage.save(new Resume("uuid_" + i));
            }
            Assertions.fail("Переполнение произошло раньше времени");
        } catch (StorageException e) {
            e.printStackTrace();
        }

        assertThrows(StorageException.class, () -> {
            storage.save(new Resume(UUID_OVERFLOW));
        });
    }
}