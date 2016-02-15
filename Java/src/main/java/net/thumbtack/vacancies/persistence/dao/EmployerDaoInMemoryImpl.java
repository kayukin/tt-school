package net.thumbtack.vacancies.persistence.dao;

import net.thumbtack.vacancies.domain.Employer;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Konstantin on 15.02.2016.
 */
public class EmployerDaoInMemoryImpl implements EmployerDao {
    private final static EmployerDao INSTANCE = new EmployerDaoInMemoryImpl();

    public static EmployerDao getInstance() {
        return INSTANCE;
    }

    private static final Map<Integer, Employer> database = new ConcurrentHashMap<>();
    private static final AtomicInteger nextId = new AtomicInteger(0);

    @Override
    public int Create(Employer employer) {
        int id = nextId.incrementAndGet();
        employer.setId(id);
        database.put(id, employer);
        return id;
    }

    @Override
    public Employer getById(int id) {
        return database.get(id);
    }

    @Override
    public Collection<Employer> getAll() {
        return database.values();
    }


}
