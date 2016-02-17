package net.thumbtack.vacancies.persistence.mybatis.mapper;

import net.thumbtack.vacancies.domain.Candidate;

import java.util.List;

/**
 * Created by Konstantin on 17.02.2016.
 */
public interface CandidateMapper {
    void create(Candidate candidate);

    Candidate getById(int id);

    List<Candidate> getAll();
}
