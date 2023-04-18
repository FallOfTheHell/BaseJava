package com.resume.webapp.storage;

import com.resume.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected Object getSearchKey(String key) {
        Resume searchKey = new Resume(key);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    protected Resume insertResume(int index, Resume resume) {
        index = -(int) getSearchKey(resume.getUuid()) -1;
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
