package com.resume.webapp.storage;

import com.resume.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    private final List<Resume> storage = new ArrayList<>();

    @Override
    protected int getIndex(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            Resume resume = storage.get(i);
            if (resume.getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void insertResume(Resume resume) {
        storage.add(resume);
    }

    @Override
    protected void removeResume(int index) {
        storage.remove(index);
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return searchKey.equals(storage);
    }

    @Override
    protected Resume doGet(int index) {
        return storage.get(index);
    }

    @Override
    protected boolean isStorageOverflow(Resume resume) {
        return false;
    }

    @Override
    protected void doUpdate(int index, Resume resume) {
        storage.set(index, resume);
    }

    @Override
    protected void doClear() {
        storage.clear();
    }

    @Override
    protected Resume[] doGetAll(int size) {
        return storage.subList(0, size).toArray(new Resume[size]);
    }
}
