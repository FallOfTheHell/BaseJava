package com.resume.webapp.storage;

import com.resume.webapp.exception.ExistStorageException;
import com.resume.webapp.exception.NotExistStorageException;
import com.resume.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected int size;

    public final Resume get(String uuid) {
        Object searchKey = getIndex(uuid);

        //TODO: проверка работает только с такой записью
        if (isExist(searchKey) || getIndex(uuid) < 0) {
            getNotExistingSearchKey(uuid);
        }

        return doGet(getIndex(uuid));
    }

    public final void save(Resume resume) {
        Object searchKey = resume.getUuid();

        isStorageOverflow(resume);

        if (isExist(searchKey)) {
            getExistingSearchKey(resume.getUuid());
        } else {
            insertResume(resume);
            size++;
            System.out.println("Resume " + resume.getUuid() + " added");
        }
    }

    public final void update(Resume resume) {
        Object searchKey = resume.getUuid();

        if (isExist(searchKey)) {
            doUpdate(getIndex(resume.getUuid()), resume);
        } else {
            getNotExistingSearchKey(resume.getUuid());
        }
    }

    public final void delete(String uuid) {
        if (isExist(uuid)) {
            removeResume(getIndex(uuid));
            size--;
        } else {
            getNotExistingSearchKey(uuid);
        }
    }

    public final void clear() {
        doClear();
        size = 0;
    }

    public Resume[] getAll() {
        return doGetAll(size);
    }

    public int size() {
        return size;
    }

    protected void getExistingSearchKey(String uuid) {
        throw new ExistStorageException(uuid);
    }

    private void getNotExistingSearchKey(String uuid) {
        throw new NotExistStorageException(uuid);
    }

    protected abstract boolean isExist(Object searchKey);

    protected abstract Resume doGet(int index);

    protected abstract boolean isStorageOverflow(Resume resume);

    protected abstract void doUpdate(int index, Resume resume);

    protected abstract void doClear();

    protected abstract Resume[] doGetAll(int size);

    protected abstract int getIndex(String uuid);

    protected abstract void insertResume(Resume resume);

    protected abstract void removeResume(int index);
}
