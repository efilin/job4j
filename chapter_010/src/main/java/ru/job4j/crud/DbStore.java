package ru.job4j.crud;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
            if (isTableEmpty(stat, "countries")) {
                addCountries(connection);
                addCities(connection);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (!isCredential("root", "root")) {
            add(new User("root", "root", "administrator", "Russia", "Moscow"));
        }
    }

    public static Store getInstance() {
        return INSTANCE;
    }

    public boolean isTableEmpty(Statement stat, String tableName) {
        boolean result = false;
        try {
            ResultSet rs = stat.executeQuery("SELECT * FROM " + tableName);
            if (!rs.next()) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void addCountries(Connection connection) {
        try (PreparedStatement pStat = connection.prepareStatement("INSERT INTO countries(name) VALUES (?);")) {
            pStat.setString(1, "Russia");
            pStat.addBatch();
            pStat.setString(1, "USA");
            pStat.addBatch();
            pStat.setString(1, "Germany");
            pStat.addBatch();
            pStat.executeBatch();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addCities(Connection connection) {
        List<String> countryList = Arrays.asList(
                "Moscow", "SPB", "Sochi", "NY", "LA", "Chicago", "Berlin", "Munich", "Hamburg");
        int counter = 0;
        try (PreparedStatement pStat = connection.prepareStatement("INSERT INTO cities(country_id, name) VALUES (?,?);")) {
            for (String city : countryList) {
                counter++;
                pStat.setInt(1, (int) Math.ceil(counter / 3.0));
                pStat.setString(2, city);
                pStat.addBatch();
            }
            pStat.executeBatch();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean add(User user) {
        boolean result = false;
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement pStat = connection.prepareStatement(
                     "INSERT INTO users(name, login, email, create_date, password, role, country, city) values (?,?,?,?,?,?,?,?);")
        ) {
            pStat.setString(1, user.getName());
            pStat.setString(2, user.getLogin());
            pStat.setString(3, user.getEmail());
            pStat.setString(4, user.getCreateDate());
            pStat.setString(5, user.getPassword());
            pStat.setString(6, user.getRole());
            pStat.setString(7, user.getCountry());
            pStat.setString(8, user.getCity());
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
                     "UPDATE users SET name = ?, login = ?, email = ?, create_date = ?, password = ?, role = ?, country = ?, city = ? WHERE id = ?")
        ) {
            pStat.setString(1, user.getName());
            pStat.setString(2, user.getLogin());
            pStat.setString(3, user.getEmail());
            pStat.setString(4, user.getCreateDate());
            pStat.setString(5, user.getPassword());
            pStat.setString(6, user.getRole());
            pStat.setString(7, user.getCountry());
            pStat.setString(8, user.getCity());
            pStat.setInt(9, id);
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
                User user = new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("password"),
                        rs.getString("role"),
                        rs.getString("country"),
                        rs.getString("city"));
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
                result = new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("password"),
                        rs.getString("role"),
                        rs.getString("country"),
                        rs.getString("city"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean isCredential(String name, String password) {
        User result = null;
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement pStat = connection.prepareStatement("SELECT * FROM users WHERE name=? AND password=?")) {
            pStat.setString(1, name);
            pStat.setString(2, password);
            ResultSet rs = pStat.executeQuery();
            while (rs.next()) {
                result = new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("password"),
                        rs.getString("role"),
                        rs.getString("country"),
                        rs.getString("city"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result != null;
    }

    @Override
    public String userRole(String name, String password) {
        String result = null;
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement pStat = connection.prepareStatement("SELECT * FROM users WHERE name=? AND password=?")) {
            pStat.setString(1, name);
            pStat.setString(2, password);
            ResultSet rs = pStat.executeQuery();
            while (rs.next()) {
                result = rs.getString("role");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<String> getCities(String country) {
        List<String> result = new ArrayList<>();
        String sql = String.format(
                "SELECT ci.name from cities as ci "
                        + "LEFT OUTER JOIN countries as co ON ci.country_id=co.id "
                        + "where co.name='%s';", country);
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement pStat = connection.prepareStatement(sql)) {
            ResultSet rs = pStat.executeQuery();
            while (rs.next()) {
                result.add(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
