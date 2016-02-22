package net.thumbtack.vacancies.persistence.mybatis.mapper;

import net.thumbtack.vacancies.domain.User;

/**
 * Created by Konstantin on 22.02.2016.
 */
public interface UserMapper {
    void createUser(User user);

    User getByLogin(String login);
}
