package com.resume.webapp.storage;

import com.resume.webapp.exception.StorageException;
import com.resume.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {

    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];

    protected int size;

    @Override
    protected Resume doGet(Object searchKey) {
        return storage[(int) searchKey];
    }

    public void doSave(Object key, Resume resume) {
        if (size >= storage.length) {
            throw new StorageException("Storage overflow", resume.getUuid());
        }
        insertResume((Integer) key, resume);
        size++;
    }

    public void doDelete(Object searchKey){
        removeResume(searchKey);
        size--;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    @Override
    protected void doUpdate(Object searchKey, Resume resume) {
        storage[(int) searchKey] = resume;
    }

    public int size() {
        return size;
    }

    @Override
    protected boolean isExist(Object searchKey) {
        int index = (int) searchKey;
        return index >= 0;
    }

    protected abstract Resume insertResume(int index, Resume resume);

    protected abstract void removeResume(Object index);
}
