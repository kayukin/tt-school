package net.thumbtack.vacancies.persistence.dao;

import net.thumbtack.vacancies.domain.Employer;

import java.util.Collection;
import java.util.Optional;

/**
 * Created by Konstantin on 15.02.2016.
 */
public interface EmployerDao {
    int Create(Employer employer);

    Optional<Employer> getById(int id);

    Collection<Employer> getAll();
}
