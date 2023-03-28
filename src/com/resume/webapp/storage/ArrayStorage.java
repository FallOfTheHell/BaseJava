package com.resume.webapp.storage;

import com.resume.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final Resume[] storage = new Resume[10000];

    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume resume) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                storage[i] = resume;
                return;
            }
            size++;
        }
    }

    public Resume get(String uuid) {
        for (Resume resume : storage) {
            if (resume != null || resume.equals(uuid)) {
                return resume;
            }
        }
        return null;
    }

    public void delete(String uuid) {
        int index = - 1;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null && uuid.equals(storage[i].getUuid())) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            for (int i = index; i < storage.length - 1; i++) {
                storage[i] = storage[i + 1];
            }
            storage[storage.length - 1] = null;
            size--;
        } else {
            System.out.println("Resume " + uuid + " not found");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        int count = 0;
        for (Resume resume : storage) {
            if (resume != null) {
                count++;
            }
        }
        return Arrays.copyOf(storage, count);
    }

    public int size() {
        return size;
    }
}
