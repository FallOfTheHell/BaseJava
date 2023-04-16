package com.resume.webapp.storage;

import com.resume.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    protected Resume insertResume(Resume resume) {
        int index = -getSearchKey(resume.getUuid()) - 1;
        if (index < size){
            System.arraycopy(storage, index, storage, index + 1, size - index);
        }
        storage[index] = resume;
        return resume;
    }

    @Override
    protected void removeResume(int index) {
        System.arraycopy(storage, index + 1, storage, index, size - index - 1);
        storage[size - 1] = null;
    }
}
