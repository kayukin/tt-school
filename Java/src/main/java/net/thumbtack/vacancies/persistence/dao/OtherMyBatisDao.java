package net.thumbtack.vacancies.persistence.dao;

import net.thumbtack.vacancies.domain.Candidate;
import net.thumbtack.vacancies.domain.Offer;
import net.thumbtack.vacancies.domain.Skill;
import net.thumbtack.vacancies.domain.User;
import net.thumbtack.vacancies.persistence.mybatis.MyBatis;
import net.thumbtack.vacancies.persistence.mybatis.mapper.OfferMapper;
import net.thumbtack.vacancies.persistence.mybatis.mapper.SkillMapper;
import net.thumbtack.vacancies.persistence.mybatis.mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

/**
 * Created by Konstantin on 27.02.2016.
 */
public class OtherMyBatisDao implements OtherDao {
    private static final OtherMyBatisDao INSTANCE = new OtherMyBatisDao();

    private OtherMyBatisDao() {
    }

    public static OtherMyBatisDao getInstance() {
        return INSTANCE;
    }

    @Override
    public Skill getSkill(String name) {
        try (SqlSession session = MyBatis.SessionFactory().openSession()) {
            SkillMapper skillMapper = session.getMapper(SkillMapper.class);
            Skill skill = skillMapper.findByName(name);
            if (skill == null) {
                skillMapper.createSkill(new Skill(0, name));
            }
            return skill;
        }
    }

    @Override
    public void deleteSkill(int id, Candidate candidate) {
        try (SqlSession session = MyBatis.SessionFactory().openSession()) {
            SkillMapper skillMapper = session.getMapper(SkillMapper.class);
            skillMapper.deleteSkill(id, candidate.getId());
            session.commit();
        }
    }

    @Override
    public List<Offer> getOffers() {
        try (SqlSession session = MyBatis.SessionFactory().openSession()) {
            OfferMapper offerMapper = session.getMapper(OfferMapper.class);
            return offerMapper.getOffers();
        }
    }

    @Override
    public Optional<User> getByLogin(String login) {
        try (SqlSession session = MyBatis.SessionFactory().openSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            return Optional.ofNullable(mapper.getByLogin(login));
        }
    }
}
