package net.thumbtack.vacancies.persistence.mybatis.mapper;

import net.thumbtack.vacancies.domain.Candidate;
import net.thumbtack.vacancies.domain.User;

import java.util.List;

/**
 * Created by Konstantin on 17.02.2016.
 */
public interface CandidateMapper {
    void create(Candidate candidate);

    void createUser(User user);

    Candidate getById(int id);

    List<Candidate> getAll();
}
