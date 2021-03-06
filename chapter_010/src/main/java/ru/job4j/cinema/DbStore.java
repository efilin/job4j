package ru.job4j.cinema;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbStore implements Store {

    private static final BasicDataSource SOURCE = new BasicDataSource();
    private static final Store INSTANCE = new DbStore();


    public static Store getInstance() {
        return INSTANCE;
    }

    public DbStore() {
        SOURCE.setDriverClassName("org.postgresql.Driver");
        SOURCE.setUrl("jdbc:postgresql://localhost:5432/postgres");
        SOURCE.setUsername("postgres");
        SOURCE.setPassword("password");
        SOURCE.setMinIdle(5);
        SOURCE.setMaxIdle(10);
        SOURCE.setMaxOpenPreparedStatements(100);
    }

    @Override
    public boolean add(Account account, int seat) {
        boolean result = false;
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement pStat = connection.prepareStatement(
                     "INSERT INTO accounts(phone_id, name) values (?,?) "
                             + "ON CONFLICT(phone_id) "
                             + "DO NOTHING;");
             PreparedStatement ppStat = connection.prepareStatement(
                     "UPDATE halls "
                             + "SET occupied_account_id = COALESCE(occupied_account_id, ?) "
                             + "WHERE seat=?;")) {
            try {
                connection.setAutoCommit(false);
                pStat.setLong(1, account.getPhone());
                pStat.setString(2, account.getName());
                pStat.executeUpdate();
                ppStat.setLong(1, account.getPhone());
                ppStat.setInt(2, seat);
                ppStat.executeUpdate();
                connection.commit();
                result = true;
            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    @Override
    public List<Boolean> getSeats() {
        List<Boolean> result = new ArrayList<>();
        boolean occupancy;
        try (Connection connection = SOURCE.getConnection();
             Statement stat = connection.createStatement();
             ResultSet rs = stat.executeQuery("SELECT occupied_account_id FROM halls ORDER BY seat;")) {
            while (rs.next()) {
                occupancy = false;
                if (rs.getObject("occupied_account_id") != null) {
                    occupancy = true;
                }
                result.add(occupancy);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
