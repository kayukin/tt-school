package net.thumbtack.vacancies.persistence.dao;

import net.thumbtack.vacancies.domain.Candidate;
import net.thumbtack.vacancies.persistence.mybatis.MyBatis;
import net.thumbtack.vacancies.persistence.mybatis.mapper.CandidateMapper;
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
    public int create(Candidate candidate) {
        try (SqlSession session = MyBatis.getInstance().openSession()) {
            CandidateMapper mapper = session.getMapper(CandidateMapper.class);
            mapper.create(candidate);
            session.commit();
            return candidate.getId();
        }
    }

    @Override
    public Optional<Candidate> getById(int id) {
        try (SqlSession session = MyBatis.getInstance().openSession()) {
            CandidateMapper mapper = session.getMapper(CandidateMapper.class);
            return Optional.ofNullable(mapper.getById(id));
        }
    }

    @Override
    public List<Candidate> getAll() {
        try (SqlSession session = MyBatis.getInstance().openSession()) {
            CandidateMapper mapper = session.getMapper(CandidateMapper.class);
            return mapper.getAll();
        }
    }
}
