package com.resume.webapp.storage;

import com.resume.webapp.exception.ExistStorageException;
import com.resume.webapp.exception.NotExistStorageException;
import com.resume.webapp.exception.StorageException;
import com.resume.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected int size;

    public final Resume get(String uuid) {
        int index = getIndex(uuid);

        if (index < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            return processGet(index);
        }
    }

    public final void save(Resume resume) {
        int index = getIndex(resume.getUuid());

        if (isStorageOverflow()) {
            throw new StorageException("Storage overflow", resume.getUuid());
        } else if (index >= 0) {
            throw new ExistStorageException(resume.getUuid());
        } else {
            insertResume(resume);
            size++;
            System.out.println("Resume " + resume.getUuid() + " added");
        }
    }

    public final void update(Resume resume) {
        int index = getIndex(resume.getUuid());

        if (index >= 0) {
            setResume(index, resume);
        } else {
            throw new NotExistStorageException(resume.getUuid());
        }
    }

    public final void delete(String uuid) {
        int index = getIndex(uuid);

        if (index >= 0) {
            removeResume(index);
            size--;
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    public final void clear() {
        clearStorage();
        size = 0;
    }

    public Resume[] getAll() {
        return processGetAll(size);
    }

    public int size() {
        return size;
    }

    protected abstract Resume processGet(int index);

    protected abstract boolean isStorageOverflow();

    protected abstract void setResume(int index, Resume resume);

    protected abstract void clearStorage();

    protected abstract Resume[] processGetAll(int size);

    protected abstract int getIndex(String uuid);

    protected abstract void insertResume(Resume resume);

    protected abstract void removeResume(int index);
}
