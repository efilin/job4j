package ru.job4j.crud;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DbStore implements Store {

    private static final BasicDataSource SOURCE = new BasicDataSource();
    private static final Store INSTANCE = new DbStore();

    public DbStore() {

        SOURCE.setDriverClassName("org.postgresql.Driver");
        SOURCE.setUrl("jdbc:postgresql://localhost:5432/postgres");
        SOURCE.setUsername("postgres");
        SOURCE.setPassword("password");
        SOURCE.setMinIdle(5);
        SOURCE.setMaxIdle(10);
        SOURCE.setMaxOpenPreparedStatements(100);
        try {
            Connection connection = SOURCE.getConnection();
            Statement stat = connection.createStatement();
            stat.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS users(id SERIAL PRIMARY KEY, name VARCHAR(100), login VARCHAR(100),email VARCHAR(100),create_date VARCHAR(100));");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Store getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean add(User user) {
        boolean result = false;
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement pStat = connection.prepareStatement(
                     "INSERT INTO users(name, login, email, create_date) values (?,?,?,?);")
        ) {
            pStat.setString(1, user.getName());
            pStat.setString(2, user.getLogin());
            pStat.setString(3, user.getEmail());
            pStat.setString(4, user.getCreateDate());
            pStat.executeUpdate();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void update(int id, User user) {
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement pStat = connection.prepareStatement(
                     "UPDATE users SET name = ?, login = ?, email = ?, create_date = ? WHERE id = ?")
        ) {
            pStat.setString(1, user.getName());
            pStat.setString(2, user.getLogin());
            pStat.setString(3, user.getEmail());
            pStat.setString(4, user.getCreateDate());
            pStat.setInt(5, id);
            pStat.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean delete(int id) {
        boolean result = false;
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement pStat = connection.prepareStatement(
                     "DELETE FROM users WHERE id = ?")
        ) {
            pStat.setInt(1, id);
            pStat.executeUpdate();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<User> findAll() {
        List<User> result = new ArrayList<>();
        try (Connection connection = SOURCE.getConnection();
             Statement stat = connection.createStatement()) {
            ResultSet rs = stat.executeQuery("SELECT * FROM users");
            while (rs.next()) {
                User user = new User(rs.getInt("id"), rs.getString("name"));
                result.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public User findById(int id) {
        User result = null;
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement pStat = connection.prepareStatement("SELECT * FROM users WHERE id=?")) {
            pStat.setInt(1, id);
            ResultSet rs = pStat.executeQuery();
            while (rs.next()) {
                result = new User(rs.getInt("id"), rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
