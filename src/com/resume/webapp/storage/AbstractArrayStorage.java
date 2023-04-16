package com.resume.webapp.storage;

import com.resume.webapp.exception.StorageException;
import com.resume.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {

    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];

    protected int size;

    @Override
    protected Resume doGet(int index) {
        return storage[index];
    }

    public void doSave(Resume resume) {
        if (size >= storage.length) {
            throw new StorageException("Storage overflow", resume.getUuid());
        }
        insertResume(resume);
        size++;
    }

    public void doDelete(String uuid){
        removeResume(getSearchKey(uuid));
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
    protected void doUpdate(int index, Resume resume) {
        storage[index] = resume;
    }

    public int size() {
        return size;
    }

    @Override
    protected boolean isExist(Object searchKey) {
        int index = getSearchKey(searchKey.toString());
        return index < 0;
    }

    protected abstract Resume insertResume(Resume resume);

    protected abstract void removeResume(int index);
}
