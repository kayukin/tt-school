package net.thumbtack.vacancies.persistence.dao;

import net.thumbtack.vacancies.domain.Offer;
import net.thumbtack.vacancies.domain.Skill;
import net.thumbtack.vacancies.domain.User;

import java.util.List;
import java.util.Optional;

public interface SharedDao {
    Optional<User> getByLogin(String login);

    Skill getSkill(String name);

    List<Offer> getOffers();
}
