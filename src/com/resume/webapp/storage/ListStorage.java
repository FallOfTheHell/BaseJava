package com.resume.webapp.storage;

import com.resume.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    private final List<Resume> storage = new ArrayList<>();

    @Override
    protected int getSearchKey(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            Resume resume = storage.get(i);
            if (resume.getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return !searchKey.equals(getSearchKey(searchKey.toString()));
    }

    @Override
    protected Resume doGet(int index) {
        return storage.get(index);
    }

    @Override
    protected void doSave(Resume resume) {
        storage.add(resume);
    }

    @Override
    protected void doUpdate(int index, Resume resume) {
        storage.set(index, resume);
    }

    @Override
    protected void doDelete(String uuid) {
        storage.remove(getSearchKey(uuid));
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        return storage.subList(0, size()).toArray(new Resume[size()]);
    }

    @Override
    public int size() {
        return storage.size();
    }
}
