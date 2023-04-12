package com.resume.webapp.storage;

import com.resume.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    private List<Resume> storage = new ArrayList<>();

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
    protected Resume processGet(int index) {
        return storage.get(index);
    }

    //TODO: я могу этот метод использовать как заглушку?
    @Override
    protected boolean isStorageOverflow() {
        return false;
    }

    @Override
    protected void setResume(int index, Resume resume) {
        storage.set(index, resume);
    }

    @Override
    protected void clearStorage() {
        storage.clear();
    }

    @Override
    protected Resume[] processGetAll(int size) {
        return storage.subList(0, size).toArray(new Resume[size]);
    }
}
