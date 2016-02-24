package net.thumbtack.vacancies.persistence.mybatis.mapper;

import net.thumbtack.vacancies.domain.Candidate;
import net.thumbtack.vacancies.domain.Skill;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Konstantin on 23.02.2016.
 */
public interface SkillMapper {
    void createSkill(Skill skill);

    Skill findByName(String name);

    void addSkillToCandidate(@Param("candidate") Candidate candidate, @Param("skill") Skill skill);

    List<Skill> getCandidateSkills(Candidate candidate);
}
