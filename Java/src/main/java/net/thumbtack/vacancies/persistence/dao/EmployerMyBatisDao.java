package net.thumbtack.vacancies.persistence.dao;

import net.thumbtack.vacancies.domain.Candidate;
import net.thumbtack.vacancies.domain.Employer;
import net.thumbtack.vacancies.domain.Offer;
import net.thumbtack.vacancies.domain.Requirement;
import net.thumbtack.vacancies.persistence.mybatis.MyBatis;
import net.thumbtack.vacancies.persistence.mybatis.mapper.EmployerMapper;
import net.thumbtack.vacancies.persistence.mybatis.mapper.OfferMapper;
import net.thumbtack.vacancies.persistence.mybatis.mapper.SkillMapper;
import net.thumbtack.vacancies.persistence.mybatis.mapper.UserMapper;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

/**
 * Created by Konstantin on 16.02.2016.
 */
public class EmployerMyBatisDao implements EmployerDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployerMyBatisDao.class);
    private static final EmployerMyBatisDao INSTANCE = new EmployerMyBatisDao();

    private EmployerMyBatisDao() {

    }

    public static EmployerMyBatisDao getInstance() {
        return INSTANCE;
    }

    @Override
    public int create(Employer employer) throws DuplicateCompany, DuplicateLogin {
        try (SqlSession session = MyBatis.getInstance().openSession()) {
            try {
                session.getMapper(UserMapper.class).createUser(employer);
                session.getMapper(EmployerMapper.class).create(employer);
                session.commit();
                return employer.getId();
            } catch (PersistenceException e) {
                session.rollback();
                if (e.getMessage().contains("login_UNIQUE")) {
                    throw new DuplicateLogin(e);
                } else if (e.getMessage().contains("company_UNIQUE")) {
                    throw new DuplicateCompany(e);
                }
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public Optional<Employer> getById(int id) {
        try (SqlSession session = MyBatis.getInstance().openSession()) {
            EmployerMapper mapper = session.getMapper(EmployerMapper.class);
            return Optional.ofNullable(mapper.getById(id));
        }
    }

    @Override
    public List<Employer> getAll() {
        try (SqlSession session = MyBatis.getInstance().openSession()) {
            EmployerMapper mapper = session.getMapper(EmployerMapper.class);
            return mapper.getAll();
        }
    }

    @Override
    public void addOfferToEmployer(Employer employer, Offer offer) {
        try (SqlSession session = MyBatis.getInstance().openSession()) {
            try {
                OfferMapper offerMapper = session.getMapper(OfferMapper.class);
                SkillMapper skillMapper = session.getMapper(SkillMapper.class);
                offerMapper.createOffer(employer, offer);
                for (Requirement req : offer.getRequirements()) {
                    req.setId(SharedMyBatisDao.getInstance().getSkill(req.getName()).getId());
                    offerMapper.createRequirement(req, offer);
                }
                session.commit();
            } catch (PersistenceException e) {
                session.rollback();
                throw e;
            }
        }
    }

    @Override
    public List<Candidate> getCandidates(Offer offer) {
        try (SqlSession session = MyBatis.getInstance().openSession()) {
            try {
                EmployerMapper employerMapper = session.getMapper(EmployerMapper.class);
                return employerMapper.getCandidates(offer);
            } catch (PersistenceException e) {
                throw e;
            }
        }
    }
}
