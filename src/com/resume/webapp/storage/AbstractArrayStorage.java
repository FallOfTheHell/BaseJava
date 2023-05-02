package com.resume.webapp.storage;

import com.resume.webapp.exception.StorageException;
import com.resume.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {

    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];

    protected int size;

    @Override
    protected Resume doGet(Integer searchKey) {
        return storage[searchKey];
    }

    public void doSave(Integer key, Resume resume) {
        if (size >= storage.length) {
            throw new StorageException("Storage overflow", resume.getUuid());
        }
        insertResume(key, resume);
        size++;
    }

    public void doDelete(Integer searchKey){
        removeResume(searchKey);
        size--;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public List<Resume> doGetAll() {
        return new ArrayList<>(Arrays.asList(Arrays.copyOf(storage, size)));
    }

    @Override
    protected void doUpdate(Integer searchKey, Resume resume) {
        storage[searchKey] = resume;
    }

    public int size() {
        return size;
    }

    @Override
    protected boolean isExist(Integer searchKey) {
        int index = searchKey;
        return index >= 0;
    }

    protected abstract Resume insertResume(Integer index, Resume resume);

    protected abstract void removeResume(Integer index);

    protected abstract Integer getSearchKey(String searchKey);
}
