import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        Arrays.fill(storage, null);
    }

    void save(Resume resume) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                storage[i] = resume;
                return;
            }
        }
    }

    Resume get(String uuid) {
        for (Resume resume : storage) {
            if (resume != null || resume.equals(uuid)) {
                return resume;
            }
        }
        return null;
    }

    void delete(String uuid) {
        int index = -1;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null && uuid.equals(storage[i].uuid)) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            Resume[] newStorage = new Resume[storage.length - 1];
            System.arraycopy(storage, index + 1, newStorage, index, storage.length - index - 1);
            storage = newStorage;
        } else {
            System.out.println("Resume " + uuid + " not found");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        int count = 0;
        for (Resume resume: storage) {
            if (resume != null){
                count++;
            }
        }
        return Arrays.copyOf(storage, count);
    }

    int size() {
        int count = 0;
        for (Resume resume: storage) {
            if (resume != null){
                count++;
            }
        }
        return count;
    }
}
