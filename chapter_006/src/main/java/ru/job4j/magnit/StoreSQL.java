package ru.job4j.magnit;

import java.io.File;
import java.sql.*;

public class StoreSQL {
    Connection conn;
    Config config;

    public StoreSQL(Config config) {
        this.config = config;
        databaseConnect(config.getDb());


    }

    public void databaseConnect(String dbName) {

        File file = new File(dbName);

        if (file.exists()) {
            //TODO: check table &
            try {
                Connection conn = DriverManager.getConnection(config.getUrl());
                PreparedStatement pStat = conn.prepareStatement("CREATE TABLE IF NOT EXISTS entry(field INTEGER);");
                pStat.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }


            System.out.print("This database name already exists");

        } else {

            try {
                conn = DriverManager.getConnection(config.getUrl());
                Statement stat = conn.createStatement();
                stat.executeUpdate("CREATE TABLE entry(field INTEGER);");
                if (conn != null) {
                    System.out.println("Connection to SQLite has been established.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        try {
            conn.setAutoCommit(false);
            generate(20);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void generate(int n) throws SQLException {
        Statement stat=conn.createStatement();
        for (int i = 0; i < n + 1; i++) {
            String query = String.format("INSERT INTO entry (field) VALUES (%i);)",i);
            stat.addBatch(query);

            /*pStat = conn.prepareStatement("INSERT INTO entry (field) VALUES (?);");
            pStat.setInt(1, i);
            pStat.addBatch();*/
        }
        if (stat.executeBatch().length == n) {
            conn.commit();
        } else {
            conn.rollback();
        }


        //String query = "INSERT INTO entry (field) VALUES "

    }


}
