package com.resume.webapp.storage;

import com.resume.webapp.model.Resume;

import java.util.*;

public class MapResumeStorage extends AbstractStorage<Resume> {

    private final Map<String, Resume> storage = new HashMap<>();

    @Override
    protected boolean isExist(Resume key) {
        return key != null;
    }

    @Override
    protected Resume doGet(Resume key) {
        return key;
    }

    @Override
    protected void doSave(Resume key, Resume resume) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void doUpdate(Resume key, Resume resume) {
        storage.replace(key.getUuid(), resume);
    }

    @Override
    protected void doDelete(Resume key) {
        storage.remove(key.getUuid());
    }

    @Override
    protected Resume getSearchKey(String uuid) {
        return storage.get(uuid);
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
