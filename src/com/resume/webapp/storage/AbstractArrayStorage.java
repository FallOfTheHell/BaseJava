package com.resume.webapp.storage;

import com.resume.webapp.exception.StorageException;
import com.resume.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {

    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];

    @Override
    protected boolean isExist(Object searchKey) {
        return searchKey == null;
    }

    @Override
    protected Resume doGet(int index) {
        return storage[index];
    }

    protected boolean isStorageOverflow(Resume resume) {
        if (size >= storage.length) {
            throw new StorageException("Storage overflow", resume.getUuid());
        }
        return true;
    }

    @Override
    protected void doUpdate(int index, Resume resume) {
        storage[index] = resume;
    }

    @Override
    protected void doClear() {
        Arrays.fill(storage, 0, size, null);
    }

    @Override
    protected Resume[] doGetAll(int size) {
        return Arrays.copyOf(storage, size);
    }
}
