package com.resume.webapp.storage;

import com.resume.webapp.exception.ExistStorageException;
import com.resume.webapp.exception.NotExistStorageException;
import com.resume.webapp.model.Resume;

import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;


public abstract class AbstractStorage<SK> implements Storage {

    private final Comparator<Resume> COMPARATOR = Comparator.comparing(Resume::getFullName)
            .thenComparing(Resume::getUuid);

    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    public final Resume get(String uuid) {
        LOG.info("Get " + uuid);
        return doGet(getExistingSearchKey(uuid));
    }

    public final void save(Resume resume) {
        LOG.info("Save " + resume);
        SK searchKey = getNotExistingSearchKey(resume.getUuid());
        doSave(searchKey, resume);
    }

    public final void update(Resume resume) {
        LOG.info("Update " + resume);
        doUpdate(getExistingSearchKey(resume.getUuid()), resume);
    }

    public final void delete(String uuid) {
        LOG.info("Delete " + uuid);
        doDelete(getExistingSearchKey(uuid));
    }

    public final List<Resume> getAllSorted() {
        LOG.info("getAllSorted");
        List<Resume> resumeList = doGetAll();
        resumeList.sort(COMPARATOR);
        return resumeList;
    }

    protected SK getExistingSearchKey(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            return searchKey;
        } else {
            LOG.warning("Resume " + uuid + " not exist");
            throw new NotExistStorageException(uuid);
        }
    }

    private SK getNotExistingSearchKey(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            return searchKey;
        } else {
            LOG.warning("Resume " + uuid + " already exist");
            throw new ExistStorageException(uuid);
        }
    }

    protected abstract boolean isExist(SK searchKey);

    protected abstract Resume doGet(SK searchKey);

    protected abstract void doSave(SK key, Resume resume);

    protected abstract void doUpdate(SK searchKey, Resume resume);

    protected abstract void doDelete(SK searchKey);

    protected abstract SK getSearchKey(String searchKey);

    protected abstract List<Resume> doGetAll();
}
