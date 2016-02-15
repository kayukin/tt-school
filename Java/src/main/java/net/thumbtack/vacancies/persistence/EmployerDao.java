package net.thumbtack.vacancies.persistence;

import net.thumbtack.vacancies.domain.Employer;

/**
 * Created by Konstantin on 15.02.2016.
 */
public interface EmployerDao {
    int Create(Employer employer);
    Employer getById(int id);
}
