package net.thumbtack.vacancies.persistence.dao;

import net.thumbtack.vacancies.domain.Employer;
import net.thumbtack.vacancies.persistence.mybatis.MyBatis;
import net.thumbtack.vacancies.persistence.mybatis.mapper.EmployerMapper;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

/**
 * Created by Konstantin on 16.02.2016.
 */
public class EmployerDaoMyBatisImpl implements EmployerDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployerDaoMyBatisImpl.class);

    @Override
    public int create(Employer employer) throws DuplicateEmployer {
        try (SqlSession session = MyBatis.getInstance().openSession()) {
            EmployerMapper mapper = session.getMapper(EmployerMapper.class);
            mapper.create(employer);
            session.commit();
            return employer.getId();
        } catch (PersistenceException e) {
            throw new DuplicateEmployer(e);
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
        return null;
    }
}
