package ru.job4j.cinema;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
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
    public boolean isAccountExists(Account account) {
        Account result = null;
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement pStat = connection.prepareStatement("SELECT * FROM accounts WHERE phone_id=? AND name=?")) {
            pStat.setInt(1, account.getPhone());
            pStat.setString(2, account.getName());
            ResultSet rs = pStat.executeQuery();
            while (rs.next()) {
                result = new Account(
                        rs.getString("name"),
                        rs.getInt("phone"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result != null;
    }

    @Override
    public boolean add(Account account, int seat) {
        boolean result = false;
        try (Connection connection = SOURCE.getConnection()) {
            connection.setAutoCommit(false);


            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        result = true;

        return result;
    }

    public boolean addAccount(Connection connection, Account account) {
        try (PreparedStatement pStat = connection.prepareStatement(
                "INSERT INTO accounts(name, phone) values (?,?);")) {
            pStat.setString(1, account.getName());
            pStat.setInt(2, account.getPhone());
            pStat.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addSeat(Connection connection, int id, int seat) {
        boolean result = false;
        try (PreparedStatement ppStat = connection.prepareStatement(
                "UPDATE halls SET occupied_account_id=? WHERE seat=?;")) {
            ppStat.setInt(1, id);
            ppStat.setInt(2, seat);
            ppStat.executeUpdate();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Integer> getOccupiedSeats() {
        return null;
    }
}
