package com.resume.webapp.storage;

import com.resume.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {

    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];

    @Override
    protected Resume processGet(int index){
        return storage[index];
    }

    @Override
    protected boolean isStorageOverflow(){
        return size >= storage.length;
    }

    protected void setResume(int index, Resume resume){
        storage[index] = resume;
    }

    @Override
    protected void clearStorage(){
        Arrays.fill(storage, 0, size, null);
    }

    @Override
    protected Resume[] processGetAll(int size){
        return Arrays.copyOf(storage, size);
    }
}
