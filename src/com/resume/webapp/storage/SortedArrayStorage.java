package com.resume.webapp.storage;

import com.resume.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());

        if (index != -1) {
            storage[index] = resume;
        } else {
            System.out.println("Resume " + resume.getUuid() + " not found");
        }
    }

    @Override
    public void save(Resume resume) {
        int index = Arrays.binarySearch(storage, 0, size, resume);

        if (index >= 0) {
            System.out.println("The resume " + resume.getUuid() + " already exists");
        } else {
            int insertIndex = -index - 1;
            if (size - insertIndex >= 0) {
                System.arraycopy(storage, insertIndex, storage, insertIndex + 1, size - insertIndex);
            }
            storage[insertIndex] = resume;
            size++;
            System.out.println("Resume " + resume.getUuid() + " added");
        }
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);

        if (index != -1) {
            size--;
            System.arraycopy(storage, index + 1, storage, index, size - index);
        } else {
            System.out.println("Resume " + uuid + " not found");
        }
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
