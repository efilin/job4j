package ru.job4j.magnit;

import java.sql.*;

public class StoreSQL implements AutoCloseable {
    private Connection conn;
    private Config config;

    public StoreSQL(Config config, int n) {
        this.config = config;
        try {
            conn = DriverManager.getConnection(this.config.getUrl());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        databaseConnect(n);
    }


    public void databaseConnect(int n) {

        try (Statement stat = conn.createStatement()) {
            stat.executeUpdate("DROP TABLE IF EXISTS entry;");
            stat.executeUpdate("CREATE TABLE  IF NOT EXISTS entry(field INTEGER);");
            generate(n);
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        }

    }

    public void generate(int n) throws SQLException {
        try (Statement stat = conn.createStatement()) {
            conn.setAutoCommit(false);
            for (int i = 0; i < n; i++) {
                String query = String.format("INSERT INTO entry (field) VALUES (%s);", i);
                stat.addBatch(query);
            }
            if (stat.executeBatch().length == n) {
                stat.executeBatch();
                conn.commit();
            } else {
                conn.rollback();
            }
            conn.setAutoCommit(true);
        }
    }

    @Override
    public void close() throws Exception {
        this.conn.close();
    }
}
