package net.thumbtack.vacancies.persistence.dao;

/**
 * Created by Konstantin on 17.02.2016.
 */
public class DuplicateEmployer extends RuntimeException {
    public DuplicateEmployer() {
    }

    public DuplicateEmployer(String message) {
        super(message);
    }

    public DuplicateEmployer(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateEmployer(Throwable cause) {
        super(cause);
    }
}
