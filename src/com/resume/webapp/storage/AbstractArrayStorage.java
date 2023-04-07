package com.resume.webapp.storage;

import com.resume.webapp.exception.ExistStorageException;
import com.resume.webapp.exception.NotExistStorageException;
import com.resume.webapp.exception.StorageException;
import com.resume.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;

    public final Resume get(String uuid) {
        int index = getIndex(uuid);

        if (index == -1) {
            throw new NotExistStorageException(uuid);
        }
        return storage[index];
    }

    public final void save(Resume resume) {
        int index = getIndex(resume.getUuid());

        if (size >= storage.length) {
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

        if (index != -1) {
            storage[index] = resume;
        } else {
            throw new NotExistStorageException(resume.getUuid());
        }
    }

    public final void delete(String uuid) {
        int index = getIndex(uuid);

        if (index != -1) {
            removeResume(index);
            size--;
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    protected abstract int getIndex(String uuid);

    protected abstract void insertResume(Resume resume);

    protected abstract void removeResume(int index);
}
