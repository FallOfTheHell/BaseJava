package com.resume.webapp.storage;

import com.resume.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapFillNameStorage extends AbstractStorage{

    private final Map<String, Resume> storage = new HashMap<>();

    @Override
    protected boolean isExist(Object key) {
        return storage.containsKey(getSearchKey(key.toString()).toString());
    }

    @Override
    protected Resume doGet(Object key) {
        return storage.get(getSearchKey(key.toString()).toString());
    }

    @Override
    protected void doSave(Object key, Resume resume) {
        storage.put(getSearchKey(key.toString()).toString(), resume);
    }

    @Override
    protected void doUpdate(Object key, Resume resume) {
        storage.put(getSearchKey(key.toString()).toString(), resume);
    }

    @Override
    protected void doDelete(Object key) {
        storage.remove(getSearchKey(key.toString()).toString());
    }

    @Override
    protected Object getSearchKey(String fillName) {
        for (Map.Entry<String, Resume> entry: storage.entrySet()) {
            if (entry.getValue().getUuid().equals(fillName)){
                return entry.getValue().getFillName();
            }
        }
        return fillName;
    }

    @Override
    protected List<Resume> doGetAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public int size() {
        return storage.size();
    }
}
