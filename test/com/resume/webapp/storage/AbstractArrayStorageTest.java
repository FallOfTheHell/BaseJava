package com.resume.webapp.storage;

import com.resume.webapp.exception.StorageException;
import com.resume.webapp.model.Resume;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest{

    private static final String UUID_OVERFLOW = "uuid_overflow";

    private static final int STORAGE_LIMIT = 10000;

    public AbstractArrayStorageTest(Storage storage) {
        //TODO: Тут нужно что-то передавать в конструктор или так и нужно оставить?
        super(storage);
    }

    @Test
    void saveOverflow(){
        storage.clear();
        try {
            for (int i = 0; i <= STORAGE_LIMIT; i++) {
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