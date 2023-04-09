package com.resume.webapp.storage;

import com.resume.webapp.exception.ExistStorageException;
import com.resume.webapp.exception.NotExistStorageException;
import com.resume.webapp.exception.StorageException;
import com.resume.webapp.model.Resume;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractArrayStorageTest {
    private final Storage storage;
    private final Resume resume1 = new Resume("UUID_1");
    private final Resume resume2 = new Resume("UUID_2");
    private final Resume resume3 = new Resume("UUID_3");
    private final Resume resume4 = new Resume("UUID_4");

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    public void setUp() {
        storage.clear();
        storage.save(resume1);
        storage.save(resume2);
        storage.save(resume3);
        //storage.save(resume4);
    }

    @Test
    void get() {
        assertGet(resume1);
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
        storage.update(resume1);
        assertSame(resume1, storage.get("UUID_1"));
    }

    @Test
    void delete() {
        storage.delete("UUID_3");
        assertThrows(NotExistStorageException.class, () -> {
            assertGet(resume3);
        });
        //TODO: Не понимаю, ожидаю исключение NotExistStorageException,
        // компилятор ругается на то что ожидает исключение ArrayIndexOutOfBoundsException
        // ставишь исключение AIOFBE ожидает NESE, какой-то замкнутый круг)
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
            storage.get("UUID_NOT_EXIST");
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
            storage.delete("UUID_NOT_EXIST");
        });
    }

    @Test
    void saveOverflow(){
        assertThrows(StorageException.class, () -> {
            storage.save(resume3);
            fail("Переполнение произошло раньше времени");
        });
    }
}