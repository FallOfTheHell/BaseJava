package com.resume.webapp.storage;

import com.resume.webapp.exception.NotExistStorageException;
import com.resume.webapp.model.Resume;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public abstract class AbstractArrayStorageTest {
    protected Storage storage;
    private Resume uuid1 = new Resume("uuid1");
    private final Resume uuid2 = new Resume("uuid2");
    private final Resume uuid3 = new Resume("uuid3");

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    public void setUp() {
        storage.clear();
        storage.save(uuid1);
        storage.save(uuid2);
        storage.save(uuid3);
    }

    @Test
    void get() {
        assertEquals(uuid1, storage.get("uuid1"));
    }

    @Test
    void save() {
        assertEquals(uuid1, storage.get("uuid1"));
    }

    @Test
    void update() {
        storage.update(uuid1);
        assertEquals(uuid1, storage.get(String.valueOf(uuid1)));
    }

    @Test
    void delete() {
        storage.delete("uuid1");
        //assertEquals(uuid1, storage.get("uuid1"));
        //TODO: У меня выбрасывается исключение "NullPointerException",
        // мне нужно его отработать или я что-то делаю не так?
    }

    @Test
    void clear() {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    void getAll() {
        storage.getAll();
        assertEquals(3, storage.size());
    }

    @Test
    void size() {
        assertEquals(3, storage.size());
    }

    @Test
    void getNotExist() {
        //TODO: Я не нашел как сделать в методе ожидаемое исключение
        // кроме как try-catch, или я плохо искал?
        assertThrows(NotExistStorageException.class, () -> {
            storage.get("dummy");
        });
    }
}