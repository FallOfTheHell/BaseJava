package com.resume.webapp.storage;

import com.resume.webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {

    private final Map<String, Resume> storage = new HashMap<>();

    @Override
    protected boolean isExist(Object searchKey) {
        return storage.containsKey(searchKey);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return storage.get(searchKey);
    }

    @Override
    protected void doSave(Object key,Resume resume) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void doUpdate(Object searchKey, Resume resume) {
        storage.put(searchKey.toString(), resume);
    }

    @Override
    protected void doDelete(Object searchKey) {
        storage.remove(searchKey);
    }

    @Override
    protected Object getSearchKey(String searchKey) {
        for (Map.Entry<String, Resume> entry: storage.entrySet()) {
            if (entry.getValue().getUuid().equals(searchKey)){
                return entry.getKey();
            }
        }
        return searchKey;
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        return storage.values().toArray(new Resume[size()]);
    }

    @Override
    public int size() {
        return storage.size();
    }
}
