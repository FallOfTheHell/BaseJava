package com.resume.webapp.storage;

import com.resume.webapp.model.Resume;

import java.util.Arrays;

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
        int index = getIndex(resume.getUuid());

        if (size >= storage.length) {
            System.out.println("Array is full. Can't save resume " + resume.getUuid());
        } else if (index >= 0) {
            System.out.println("The resume " + resume.getUuid() + " already exists");
        } else {
            insertResume(resume);
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
            removeResume(index);
            size--;
        } else {
            System.out.println("Resume " + uuid + " not found");
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
