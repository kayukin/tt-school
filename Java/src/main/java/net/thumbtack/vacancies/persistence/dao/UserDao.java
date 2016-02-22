package net.thumbtack.vacancies.persistence.dao;

import net.thumbtack.vacancies.domain.User;

import java.util.Optional;

/**
 * Created by Konstantin on 22.02.2016.
 */
public interface UserDao {

    Optional<User> getByLogin(String login);
}
