package com.resume.webapp.storage;

import com.resume.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    public void insertResume(Resume resume) {
        int index = -getIndex(resume.getUuid()) - 1;
        if (index < size){
            System.arraycopy(storage, index, storage, index + 1, size - index);
        }
        storage[index] = resume;
    }

    @Override
    protected void removeResume(int index) {
        System.arraycopy(storage, index + 1, storage, index, size - index - 1);
        storage[size - 1] = null;
    }
}
