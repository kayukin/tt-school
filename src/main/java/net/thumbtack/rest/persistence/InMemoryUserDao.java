package net.thumbtack.rest.persistence;

import net.thumbtack.rest.models.User;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;


public class InMemoryUserDao implements UserDao {
    private static final UserDao INSTANCE = new InMemoryUserDao();

    public static UserDao getInstance() {
        return INSTANCE;
    }

    private InMemoryUserDao() {

    }

    private static final Map<Integer, User> database = new ConcurrentHashMap<>();

    static {
        database.put(0, new User(0, "Admin", 0));
    }

    private static final AtomicInteger nextId = new AtomicInteger(0);

    @Override
    public Collection<User> getAllUsers() {
        return database.values();
    }

    @Override
    public void modify(User user) {
        User modifyUser=database.get(user.getId());

        if(user.getName()!=null)
            modifyUser.setName(user.getName());
        if(user.getAge()!=null)
            modifyUser.setAge(user.getAge());
        database.put(user.getId(), modifyUser);
    }

    @Override
    public int create(User user) {
        int id = nextId.incrementAndGet();
        database.put(id, user);
        return id;
    }

    @Override
    public User findById(int id) {
        return database.get(id);
    }

    @Override
    public void delete(int id) {
        database.remove(id);
    }

    @Override
    public void deleteAll() {
        database.clear();
    }
}
