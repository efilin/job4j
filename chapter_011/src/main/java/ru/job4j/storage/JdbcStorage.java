package ru.job4j.storage;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.job4j.beanexamples.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component
public class JdbcStorage implements Storage {

    private static final BasicDataSource SOURCE = new BasicDataSource();

    @Autowired
    public JdbcStorage() {
        SOURCE.setDriverClassName("org.postgresql.Driver");
        SOURCE.setUrl("jdbc:postgresql://localhost:5432/postgres");
        SOURCE.setUsername("postgres");
        SOURCE.setPassword("password");
        SOURCE.setMinIdle(5);
        SOURCE.setMaxIdle(10);
        SOURCE.setMaxOpenPreparedStatements(20);
    }

    @Override
    public void add(User user) {
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement pStat = connection.prepareStatement(
                     "INSERT INTO users_storage(name) values (?);")) {
            pStat.setString(1, user.getName());
            pStat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
