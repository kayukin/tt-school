package net.thumbtack.vacancies.persistence.mapper;

import net.thumbtack.vacancies.domain.Employer;

/**
 * Created by Konstantin on 16.02.2016.
 */
public interface EmployerMapper {
    int Create(Employer employer);

    Employer getById(int id);
}
