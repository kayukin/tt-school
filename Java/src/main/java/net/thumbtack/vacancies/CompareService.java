package net.thumbtack.vacancies;

import net.thumbtack.vacancies.domain.Candidate;
import net.thumbtack.vacancies.domain.Requirement;

import java.util.List;

public interface CompareService {
    List<Candidate> filterCandidates(List<Candidate> candidates, List<Requirement> requirements);
}
