package com.resume.webapp.storage;

import com.resume.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    private final List<Resume> storage = new ArrayList<>();

    @Override
    protected Object getSearchKey(String searchKey) {
        for (int i = 0; i < storage.size(); i++) {
            Resume resume = storage.get(i);
            if (resume.getUuid().equals(searchKey)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return (int) searchKey >= 0;
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return storage.get((Integer) searchKey);
    }

    @Override
    protected void doSave(Object key, Resume resume) {
        storage.add(resume);
    }

    @Override
    protected void doUpdate(Object searchKey, Resume resume) {
        storage.set((Integer) searchKey, resume);
    }

    @Override
    protected void doDelete(Object searchKey) {
        storage.remove(doGet(searchKey));
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
