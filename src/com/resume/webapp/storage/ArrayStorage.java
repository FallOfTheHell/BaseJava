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
        int length = storage.length;
        if (length == size) {
            System.out.println("Array is full. Can't save resume " + resume.getUuid());
            return;
        }

        for (int i = 0; i < length; i++) {
            if (storage[i] == null) {
                storage[i] = resume;
                size++;
                //TODO: правильно ли я ввожу в консоль инфу?
                // "save на отсутствие резюме в storage"
                System.out.println("Resume " + resume.getUuid() + " has not been added yet");
                return;
            }
        }
    }

    public void update(Resume resume) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(resume.getUuid())) {
                index = i;
                break;
            }
        }

        if (index >= 0) {
            storage[index] = resume;
        } else {
            System.out.println("Resume " + resume.getUuid() + " not found");
        }
    }

    public Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i] != null && storage[i].getUuid().equals(uuid)) {
                return storage[i];
            }
        }
        System.out.println("Resume " + uuid + " not found");
        return null;
    }

    public void delete(String uuid) {
        int length = storage.length;
        int index = -1;
        for (int i = 0; i < length; i++) {
            if (storage[i] != null && uuid.equals(storage[i].getUuid())) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            for (int i = index; i < length - 1; i++) {
                storage[i] = storage[i + 1];
            }
            storage[length - 1] = null;
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
