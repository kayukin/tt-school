package net.thumbtack.vacancies.persistence.dao;

import net.thumbtack.vacancies.domain.Candidate;
import net.thumbtack.vacancies.domain.Skill;
import net.thumbtack.vacancies.persistence.dao.exceptions.DuplicateLogin;
import net.thumbtack.vacancies.persistence.mybatis.MyBatis;
import net.thumbtack.vacancies.persistence.mybatis.mapper.CandidateMapper;
import net.thumbtack.vacancies.persistence.mybatis.mapper.SkillMapper;
import net.thumbtack.vacancies.persistence.mybatis.mapper.UserMapper;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

/**
 * Created by Konstantin on 17.02.2016.
 */
public class CandidateMyBatisDao implements CandidateDao {

    private final static Logger LOGGER = LoggerFactory.getLogger(CandidateMyBatisDao.class);
    private final static CandidateMyBatisDao INSTANCE = new CandidateMyBatisDao();

    private CandidateMyBatisDao() {
    }

    public static CandidateMyBatisDao getInstance() {
        return INSTANCE;
    }

    @Override
    public int create(Candidate candidate) throws DuplicateLogin {
        try (SqlSession session = MyBatis.SessionFactory().openSession()) {
            try {
                session.getMapper(UserMapper.class).createUser(candidate);
                session.getMapper(CandidateMapper.class).create(candidate);
                session.commit();
                return candidate.getId();
            } catch (PersistenceException e) {
                session.rollback();
                if (e.getMessage().contains("login_UNIQUE")) {
                    throw new DuplicateLogin(e);
                }
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public Optional<Candidate> getById(int id) {
        try (SqlSession session = MyBatis.SessionFactory().openSession()) {
            CandidateMapper mapper = session.getMapper(CandidateMapper.class);
            return Optional.ofNullable(mapper.getById(id));
        }
    }

    @Override
    public List<Candidate> getAll() {
        try (SqlSession session = MyBatis.SessionFactory().openSession()) {
            CandidateMapper mapper = session.getMapper(CandidateMapper.class);
            return mapper.getAll();
        }
    }

    @Override
    public void addSkillToCandidate(Candidate candidate, Skill skill) {
        try (SqlSession session = MyBatis.SessionFactory().openSession()) {
            SkillMapper skillMapper = session.getMapper(SkillMapper.class);
            skill.setId(OtherMyBatisDao.getInstance().getSkill(skill.getName()).getId());
            skillMapper.addSkillToCandidate(candidate, skill);
            session.commit();
        }
    }

    @Override
    public void changeLevel(Candidate candidate, Skill skill) {
        try (SqlSession session = MyBatis.SessionFactory().openSession()) {
            SkillMapper skillMapper = session.getMapper(SkillMapper.class);
            skillMapper.changeLevel(skill.getId(), candidate.getId(), skill.getLevel());
            session.commit();
        }
    }
}
