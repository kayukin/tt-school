package net.thumbtack.vacancies.persistence.dao;

/**
 * Created by Konstantin on 20.02.2016.
 */
public class DuplicateLogin extends RuntimeException {
    public DuplicateLogin() {
    }

    public DuplicateLogin(String message) {
        super(message);
    }

    public DuplicateLogin(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateLogin(Throwable cause) {
        super(cause);
    }
}
