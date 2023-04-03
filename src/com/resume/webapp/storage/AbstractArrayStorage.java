package com.resume.webapp.storage;

import com.resume.webapp.model.Resume;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;

    public Resume get(String uuid) {
        int index = getIndex(uuid);

        if (index == -1) {
            System.out.println("Resume " + uuid + " not exist");
            return null;
        }
        return storage[index];
    }

    public void save(Resume resume) {
        if (size >= storage.length) {
            System.out.println("Array is full. Can't save resume " + resume.getUuid());
        } else if (getIndex(resume.getUuid()) != -1) {
            System.out.println("The resume " + resume.getUuid() + " already exists");
        } else {
            storage[size] = resume;
            size++;
            System.out.println("Resume " + resume.getUuid() + " added");
        }
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());

        if (index != -1) {
            storage[index] = resume;
        } else {
            System.out.println("Resume " + resume.getUuid() + " not found");
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);

        if (index != -1) {
            size--;
            System.arraycopy(storage, index + 1, storage, index, size - index);
        } else {
            System.out.println("Resume " + uuid + " not found");
        }
    }

    protected abstract int getIndex(String uuid);
}
