package net.thumbtack.rest.persistence;


import net.thumbtack.rest.models.User;

import java.util.Collection;


public interface UserDao {

    int create(User user);

    User findById(int id);

    void delete(int id);

    void deleteAll();

    void modify(User user);

    Collection<User> getAllUsers();
}
