package net.thumbtack.vacancies.services;

import net.thumbtack.vacancies.domain.User;

public interface TokenService {
    String createToken(User user);

    int getUserId(String token);

    boolean isValid(String token);
}
