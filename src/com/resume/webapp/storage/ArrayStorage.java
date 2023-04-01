package com.resume.webapp.storage;

import com.resume.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {

    private final int STORAGE_LIMIT = 10_000;

    private final Resume[] storage = new Resume[STORAGE_LIMIT];

    private int size;

    public void save(Resume resume) {
        if (size >= storage.length) {
            System.out.println("Array is full. Can't save resume " + resume.getUuid());
        } else if (getResume(resume.getUuid()) != -1) {
            System.out.println("The resume " + resume.getUuid() + " already exists");
        } else {
            storage[size] = resume;
            size++;
            System.out.println("Resume " + resume.getUuid() + " added");
        }
    }

    public void update(Resume resume) {
        int index = getResume(resume.getUuid());

        if (index != -1) {
            storage[index] = resume;
        } else {
            System.out.println("Resume " + resume.getUuid() + " not found");
        }
    }

    public Resume get(String uuid) {
        int index = getResume(uuid);

        if (index != -1) {
            return storage[index];
        } else {
            System.out.println("Resume " + uuid + " not found");
            return null;
        }
    }

    public void delete(String uuid) {
        int index = getResume(uuid);

        if (index != -1) {
            size--;
            System.arraycopy(storage, index + 1, storage, index, size - index);
        } else {
            System.out.println("Resume " + uuid + " not found");
        }
    }

    protected int getResume(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public int size() {
        return size;
    }
}
