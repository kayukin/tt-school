package net.thumbtack.rest.persistence;

import net.thumbtack.rest.models.User;

import java.util.ArrayList;
import java.util.Collection;
import java.sql.*;
import java.util.List;

/**
 * Created by Konstantin on 01.02.2016.
 */
public class JdbcUserDao implements UserDao {

    private Connection conn;
    private final String username = "root";
    private final String password = "root";
    private final String url = "jdbc:mysql://localhost:3306/mydb";

    public JdbcUserDao() {
        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int create(User user) {
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("INSERT INTO users (name, age) VALUES ('" + user.getName() + "'," + user.getAge() + ")");
            ResultSet rs = stmt.executeQuery("SELECT @@IDENTITY AS id");
            rs.first();
            return rs.getInt("id");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User findById(int id) {
        User user = null;
        try (Statement stmt = conn.createStatement();) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE users.id=" + id);
            if (!rs.first())
                return user;
            user = new User(rs.getInt("id"), rs.getString("name"), rs.getInt("age"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public void delete(int id) {
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("DELETE FROM users WHERE id=" + id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteAll() {
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("DELETE FROM users");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void modify(User user) {
        User oldUser = null;
        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE id=?")) {
            stmt.setInt(1, user.getId());
            ResultSet resultSet = stmt.executeQuery();
            resultSet.first();
            oldUser = processRow(resultSet);
            if (user.getAge() != null)
                oldUser.setAge(user.getAge());
            if (user.getName() != null)
                oldUser.setName(user.getName());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try (PreparedStatement stmt = conn.prepareStatement("UPDATE users SET age=?, name=? WHERE id=?")) {
            stmt.setInt(3, oldUser.getId());
            stmt.setInt(1, oldUser.getAge());
            stmt.setString(2, oldUser.getName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Collection<User> getAllUsers() {
        Collection<User> result = new ArrayList<>();
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM users");
            while (rs.next()) {
                result.add(processRow(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    private User processRow(ResultSet rs) throws SQLException {
        return new User(rs.getInt("id"), rs.getString("name"), rs.getInt("age"));
    }
}
