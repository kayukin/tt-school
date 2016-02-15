package net.thumbtack.vacancies.persistence.dao;

import net.thumbtack.vacancies.domain.Employer;

import java.util.Collection;

/**
 * Created by Konstantin on 15.02.2016.
 */
public interface EmployerDao {
    int Create(Employer employer);

    Employer getById(int id);

    Collection<Employer> getAll();
}
