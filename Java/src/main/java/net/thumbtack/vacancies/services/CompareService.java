package net.thumbtack.vacancies.services;

import net.thumbtack.vacancies.domain.Candidate;
import net.thumbtack.vacancies.domain.Offer;
import net.thumbtack.vacancies.domain.Requirement;
import net.thumbtack.vacancies.domain.Skill;

import java.util.List;

public interface CompareService {
    List<Candidate> filterCandidates(List<Candidate> candidates, List<Requirement> requirements);

    List<Offer> filterOffers(List<Offer> offers, List<Skill> skills);
}
