package ru.job4j.magnit;

import java.io.File;
import java.sql.*;

public class StoreSQL {
    Connection conn;
    Config config;

    public StoreSQL(Config config) {
        this.config = config;
        databaseConnect();


    }

    public void databaseConnect() {

       /*File file = new File(dbName);

        if (file.exists()) {
            //TODO: check table &
            try {
                Connection conn = DriverManager.getConnection(config.getUrl());
                PreparedStatement pStat = conn.prepareStatement("CREATE TABLE IF NOT EXISTS entry(field INTEGER);");
                pStat.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }*/


//            System.out.print("This database name already exists");

//        } else {

        try {
            conn = DriverManager.getConnection(config.getUrl());
            Statement stat = conn.createStatement();
            stat.executeUpdate("DROP TABLE IF EXISTS entry;");
            stat.executeUpdate("CREATE TABLE  IF NOT EXISTS entry(field INTEGER);");
            if (conn != null) {
                System.out.println("Connection to SQLite has been established.");
            }
            conn.setAutoCommit(false);
            generate(1000000);
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

}
