package net.thumbtack.vacancies.persistence.dao;

import net.thumbtack.vacancies.domain.Employer;

import java.util.Collection;
import java.util.Optional;

/**
 * Created by Konstantin on 16.02.2016.
 */
public class EmployerDaoMyBatisImpl implements EmployerDao {
    @Override
    public int Create(Employer employer) {
        return 0;
    }

    @Override
    public Optional<Employer> getById(int id) {
        return null;
    }

    @Override
    public Collection<Employer> getAll() {
        return null;
    }
}
