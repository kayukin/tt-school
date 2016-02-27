package net.thumbtack.vacancies.persistence.dao;

import net.thumbtack.vacancies.domain.Skill;

/**
 * Created by Konstantin on 27.02.2016.
 */
public interface SkillDao {
    Skill getSkill(String name);
}
