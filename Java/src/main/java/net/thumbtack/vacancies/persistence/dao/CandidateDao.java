package net.thumbtack.vacancies.persistence.dao;

import net.thumbtack.vacancies.domain.Candidate;
import net.thumbtack.vacancies.domain.Skill;
import net.thumbtack.vacancies.persistence.dao.exceptions.DuplicateLogin;

import java.util.List;
import java.util.Optional;

/**
 * Created by Konstantin on 17.02.2016.
 */
public interface CandidateDao {
    int create(Candidate candidate) throws DuplicateLogin;

    Optional<Candidate> getById(int id);

    List<Candidate> getAll();

    void addSkillToCandidate(Candidate candidate, Skill skill);

    void changeLevel(Candidate candidate, Skill skill);
}
