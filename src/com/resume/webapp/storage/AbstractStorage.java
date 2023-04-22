package com.resume.webapp.storage;

import com.resume.webapp.exception.ExistStorageException;
import com.resume.webapp.exception.NotExistStorageException;
import com.resume.webapp.model.Resume;

import java.util.*;


public abstract class AbstractStorage implements Storage {

    public final Resume get(String uuid) {
        return doGet(getExistingSearchKey(uuid));
    }

    public final void save(Resume resume) {
        Object searchKey = getNotExistingSearchKey(resume.getUuid());
        doSave(searchKey, resume);
        System.out.println("Resume " + resume.getUuid() + " added");
    }

    public final void update(Resume resume) {
        doUpdate(getExistingSearchKey(resume.getUuid()), resume);
    }

    public final void delete(String uuid) {
        doDelete(getExistingSearchKey(uuid));
    }

    public final List<Resume> getAllSorted(){
        Comparator<Resume> comparator = Comparator.comparing(Resume::getFillName)
                .thenComparing(Resume::getUuid);
        doGetAll().sort(comparator);
        return doGetAll();
    }

    protected Object getExistingSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            return searchKey;
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    private Object getNotExistingSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            return searchKey;
        } else {
            throw new ExistStorageException(uuid);
        }
    }

    protected abstract boolean isExist(Object searchKey);

    protected abstract Resume doGet(Object searchKey);

    protected abstract void doSave(Object key,Resume resume);

    protected abstract void doUpdate(Object searchKey, Resume resume);

    protected abstract void doDelete(Object searchKey);

    protected abstract Object getSearchKey(String searchKey);

    protected abstract List<Resume> doGetAll();
}
