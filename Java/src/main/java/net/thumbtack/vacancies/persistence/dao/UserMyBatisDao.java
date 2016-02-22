package net.thumbtack.vacancies.persistence.dao;

import net.thumbtack.vacancies.domain.User;
import net.thumbtack.vacancies.persistence.mybatis.MyBatis;
import net.thumbtack.vacancies.persistence.mybatis.mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 * Created by Konstantin on 22.02.2016.
 */
public class UserMyBatisDao implements UserDao {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserMyBatisDao.class);
    private final static UserMyBatisDao INSTANCE = new UserMyBatisDao();

    private UserMyBatisDao() {
    }

    public static UserMyBatisDao getInstance() {
        return INSTANCE;
    }

    @Override
    public Optional<User> getByLogin(String login) {
        try (SqlSession session = MyBatis.getInstance().openSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            return Optional.ofNullable(mapper.getByLogin(login));
        }
    }
}
