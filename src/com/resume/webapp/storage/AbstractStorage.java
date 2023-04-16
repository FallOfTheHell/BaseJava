package com.resume.webapp.storage;

import com.resume.webapp.exception.ExistStorageException;
import com.resume.webapp.exception.NotExistStorageException;
import com.resume.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public final Resume get(String uuid) {
        getNotExistingSearchKey(uuid);
        return doGet(getSearchKey(uuid));
    }

    public final void save(Resume resume) {
        doSave(resume);
        getExistingSearchKey(resume.getUuid());
        System.out.println("Resume " + resume.getUuid() + " added");
    }

    public final void update(Resume resume) {
        getNotExistingSearchKey(resume.getUuid());
        doUpdate(getSearchKey(resume.getUuid()), resume);
    }

    public final void delete(String uuid) {
        getNotExistingSearchKey(uuid);
        doDelete(uuid);
    }

    protected Object getExistingSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        } else {
            return searchKey;
        }
    }

    private Object getNotExistingSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        } else {
            return searchKey;
        }
    }

    protected abstract boolean isExist(Object searchKey);

    protected abstract Resume doGet(int index);

    protected abstract void doSave(Resume resume);

    protected abstract void doUpdate(int index, Resume resume);

    protected abstract void doDelete(String uuid);

    protected abstract int getSearchKey(String uuid);
}
