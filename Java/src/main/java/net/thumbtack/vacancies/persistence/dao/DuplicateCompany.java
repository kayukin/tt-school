package net.thumbtack.vacancies.persistence.dao;

/**
 * Created by Konstantin on 17.02.2016.
 */
public class DuplicateCompany extends RuntimeException {
    public DuplicateCompany() {
    }

    public DuplicateCompany(String message) {
        super(message);
    }

    public DuplicateCompany(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateCompany(Throwable cause) {
        super(cause);
    }
}
