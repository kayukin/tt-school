package net.thumbtack.vacancies.persistence.dao;

import net.thumbtack.vacancies.domain.Candidate;
import net.thumbtack.vacancies.domain.Employer;
import net.thumbtack.vacancies.domain.Offer;
import net.thumbtack.vacancies.persistence.dao.exceptions.DuplicateCompany;
import net.thumbtack.vacancies.persistence.dao.exceptions.DuplicateLogin;

import java.util.List;
import java.util.Optional;

/**
 * Created by Konstantin on 15.02.2016.
 */
public interface EmployerDao {
    int create(Employer employer) throws DuplicateCompany, DuplicateLogin;

    Optional<Employer> getById(int id);

    List<Employer> getAll();

    void addOfferToEmployer(Employer employer, Offer offer);

    List<Candidate> getCandidates(Offer offer);
}
