package net.thumbtack.vacancies.persistence.mybatis.mapper;

import net.thumbtack.vacancies.domain.Candidate;
import net.thumbtack.vacancies.domain.Employer;
import net.thumbtack.vacancies.domain.Offer;

import java.util.List;

/**
 * Created by Konstantin on 16.02.2016.
 */
public interface EmployerMapper {
    void create(Employer employer);

    Employer getById(int id);

    List<Employer> getAll();
    List<Candidate> getCandidates(Offer offer);
}
