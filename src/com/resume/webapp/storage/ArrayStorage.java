package com.resume.webapp.storage;

import com.resume.webapp.model.Resume;

public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected Object getSearchKey(String searchKey) {
        for (int i = 0; i < size; i++) {
            if (searchKey.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected Resume insertResume(int index, Resume resume) {
        storage[size] = resume;
        return resume;
    }

    @Override
    protected void removeResume(Object index) {
        storage[(int) index] = storage[size - 1];
        storage[size - 1] = null;
    }
}
