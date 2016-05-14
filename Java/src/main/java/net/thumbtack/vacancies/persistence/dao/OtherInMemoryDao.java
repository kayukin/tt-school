package net.thumbtack.vacancies.persistence.dao;

import net.thumbtack.vacancies.domain.Candidate;
import net.thumbtack.vacancies.domain.Offer;
import net.thumbtack.vacancies.domain.Skill;
import net.thumbtack.vacancies.domain.User;

import java.util.List;
import java.util.Optional;

public class OtherInMemoryDao implements OtherDao {
    private static final OtherDao INSTANCE = new OtherInMemoryDao();

    public static OtherDao getInstance() {
        return INSTANCE;
    }

    private OtherInMemoryDao() {

    }

    @Override
    public Optional<User> getByLogin(String login) {
        return null;
    }

    @Override
    public Skill getSkill(String name) {
        return null;
    }

    @Override
    public void deleteSkill(int id, Candidate candidate) {

    }

    @Override
    public List<Offer> getOffers() {
        return null;
    }
}
