package net.thumbtack.vacancies;

import net.thumbtack.vacancies.domain.Candidate;
import net.thumbtack.vacancies.domain.Requirement;
import net.thumbtack.vacancies.domain.Skill;

import java.util.ArrayList;
import java.util.List;

public class CompareService {
    private CompareService() {

    }

    private static final CompareService INSTANCE = new CompareService();

    public static CompareService getInstance() {
        return INSTANCE;
    }

    public List<Candidate> filterCandidates(List<Candidate> candidates, List<Requirement> requirements) {
        List<Candidate> list = new ArrayList<>();
        for (Candidate candidate : candidates) {
            if (isAccept(candidate.getSkills(), requirements)) {
                list.add(candidate);
            }
        }
        return list;
    }

    public boolean isAccept(List<Skill> skills, List<Requirement> requirements) {
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