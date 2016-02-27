package net.thumbtack.vacancies.persistence.dao;

import net.thumbtack.vacancies.domain.Employer;
import net.thumbtack.vacancies.domain.Offer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Konstantin on 15.02.2016.
 */
public class EmployerInMemoryDao implements EmployerDao {
    private final static EmployerDao INSTANCE = new EmployerInMemoryDao();

    public static EmployerDao getInstance() {
        return INSTANCE;
    }

    private static final Map<Integer, Employer> database = new ConcurrentHashMap<>();

    static {
        database.put(1, new Employer(1, "job@thumbtack.net", "Ivan", "Ivanov"
                , "login", "pass", "thumbtack", "Jukova", null));
    }

    private static final AtomicInteger nextId = new AtomicInteger(0);

    @Override
    public int create(Employer employer) {
        int id = nextId.incrementAndGet();
        employer.setId(id);
        database.put(id, employer);
        return id;
    }

    @Override
    public Optional<Employer> getById(int id) {
        return Optional.ofNullable(database.get(id));
    }

    @Override
    public List<Employer> getAll() {
        return new ArrayList<>(database.values());
    }

    @Override
    public void addOfferToEmployer(Employer employer, Offer offer) {
        //TODO
    }


}
