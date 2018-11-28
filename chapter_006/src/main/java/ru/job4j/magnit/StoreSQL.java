package ru.job4j.magnit;

import java.sql.*;

public class StoreSQL {
    private Connection conn;
    private Config config;

    public StoreSQL(Config config, int n) {
        this.config = config;
        databaseConnect(n);

    }


    public void databaseConnect(int n) {

        try {
            conn = DriverManager.getConnection(config.getUrl());
            Statement stat = conn.createStatement();
            stat.executeUpdate("DROP TABLE IF EXISTS entry;");
            stat.executeUpdate("CREATE TABLE  IF NOT EXISTS entry(field INTEGER);");
            /*if (conn != null) {
                System.out.println("Connection to SQLite has been established.");
            }*/
            conn.setAutoCommit(false);
            generate(n);
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        }

    }

    public void generate(int n) throws SQLException {
        Statement stat = conn.createStatement();
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
    }

    public Connection getConn() {
        return conn;
    }
}
