package net.thumbtack.vacancies;

import net.thumbtack.vacancies.domain.Candidate;
import net.thumbtack.vacancies.domain.Offer;
import net.thumbtack.vacancies.domain.Requirement;
import net.thumbtack.vacancies.domain.Skill;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CompareServiceImpl implements CompareService {
    private CompareServiceImpl() {

    }

    private static final CompareServiceImpl INSTANCE = new CompareServiceImpl();

    public static CompareServiceImpl getInstance() {
        return INSTANCE;
    }

    public List<Candidate> filterCandidates(List<Candidate> candidates, List<Requirement> requirements) {
        return candidates
                .stream()
                .filter(candidate -> isAccept(candidate.getSkills(), requirements))
                .collect(Collectors.toList());
    }

    @Override
    public List<Offer> filterOffers(List<Offer> offers, List<Skill> skills) {
        return offers
                .stream()
                .filter(offer -> isAccept(skills, offer.getRequirements()))
                .collect(Collectors.toList());
    }

    boolean isAccept(List<Skill> skills, List<Requirement> requirements) {
        for (Requirement requirement : requirements) {
            Skill skillFromReq = new Skill(requirement.getId(), requirement.getName(), requirement.getLevel());
            if (!skills.contains(skillFromReq)) {
                return false;
            }
            if (skills.get(skills.indexOf(skillFromReq)).getLevel() < requirement.getLevel()) {
                return false;
            }
        }
        return true;
    }
}