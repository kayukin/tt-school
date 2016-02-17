package net.thumbtack.vacancies.persistence.dao;

import net.thumbtack.vacancies.domain.Candidate;

import java.util.List;
import java.util.Optional;

/**
 * Created by Konstantin on 17.02.2016.
 */
public interface CandidateDao {
    int create(Candidate candidate);

    Optional<Candidate> getById(int id);

    List<Candidate> getAll();
}
