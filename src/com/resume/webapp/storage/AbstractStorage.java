package com.resume.webapp.storage;

import com.resume.webapp.exception.ExistStorageException;
import com.resume.webapp.exception.NotExistStorageException;
import com.resume.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public final Resume get(String uuid) {
        return doGet(getNotExistingSearchKey(uuid));
    }

    public final void save(Resume resume) {
        Object searchKey = getExistingSearchKey(resume.getUuid());
        doSave(searchKey, resume);
        System.out.println("Resume " + resume.getUuid() + " added");
    }

    public final void update(Resume resume) {
        doUpdate(getNotExistingSearchKey(resume.getUuid()), resume);
    }

    public final void delete(String uuid) {
        doDelete(getNotExistingSearchKey(uuid));
    }

    protected Object getExistingSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            return searchKey;
        } else {
            throw new ExistStorageException(uuid);
        }
    }

    private Object getNotExistingSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            return searchKey;
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    protected abstract boolean isExist(Object searchKey);

    protected abstract Resume doGet(Object searchKey);

    protected abstract void doSave(Object key,Resume resume);

    protected abstract void doUpdate(Object searchKey, Resume resume);

    protected abstract void doDelete(Object searchKey);

    protected abstract Object getSearchKey(String searchKey);
}
