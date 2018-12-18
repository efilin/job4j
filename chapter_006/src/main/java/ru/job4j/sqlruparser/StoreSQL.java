package ru.job4j.sqlruparser;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.io.InputStream;
import java.sql.*;
import java.util.List;
import java.util.Properties;

public class StoreSQL implements AutoCloseable {
    private Connection conn;
    private List<Vacancy> vacancyList;
    private int numberOfStarts;
    private static final Logger LOG = LogManager.getLogger(StoreSQL.class.getName());

    public StoreSQL(String config) {
        init(config);
        try {
            Statement stat = this.conn.createStatement();
            stat.executeUpdate("CREATE TABLE IF NOT EXISTS vacancy("
                    + "id SERIAL PRIMARY KEY, "
                    + "name VARCHAR(300), "
                    + "description VARCHAR(10000),"
                    + "url VARCHAR(300),"
                    + "createDate TIMESTAMP);");
            stat.executeUpdate("CREATE TABLE IF NOT EXISTS version("
                    + "run_number SERIAL PRIMARY KEY, "
                    + "description VARCHAR(300))");
            LOG.info("connecting and creating tables");
            stat.executeUpdate("INSERT INTO version (description) VALUES ('default description');");
            ResultSet rs = stat.executeQuery("SELECT COUNT(description) FROM version;");
            rs.next();
            this.numberOfStarts = rs.getInt("count");
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public boolean init(String config) {
        try (InputStream in = StoreSQL.class.getClassLoader().getResourceAsStream(config)) {
            Properties properties = new Properties();
            properties.load(in);
            Class.forName(properties.getProperty("driver-class-name"));
            this.conn = DriverManager.getConnection(
                    properties.getProperty("url"),
                    properties.getProperty("username"),
                    properties.getProperty("password")
            );
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new IllegalStateException(e);
        }
        return this.conn != null;
    }

    public boolean checkDoublesInDb(Vacancy vacancy) {
        String vacancyName = vacancy.getName();
        try (PreparedStatement pStat = this.conn
                .prepareStatement("SELECT * FROM vacancy WHERE name = ?")) {
            pStat.setString(1, vacancyName);
            ResultSet rs = pStat.executeQuery();
            if (!rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return false;
    }

    public void addVacancyList(List<Vacancy> vacancyList) throws SQLException {
        String sql = "INSERT INTO vacancy(name, description, url, createDate) values (?,?,?,?);";
        try (PreparedStatement pStat = conn.prepareStatement(sql)) {
            conn.setAutoCommit(false);
            for (Vacancy vacancy : vacancyList) {
                if (checkDoublesInDb(vacancy)) {
                    pStat.setString(1, vacancy.getName());
                    pStat.setString(2, vacancy.getText());
                    pStat.setString(3, vacancy.getUrl());
                    pStat.setTimestamp(4, Timestamp.valueOf(vacancy.getDate()));
                    pStat.addBatch();
                }
            }
            pStat.executeBatch();
            conn.commit();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            conn.rollback();
        }
        conn.setAutoCommit(true);
    }

    public void clearVacancyList() {
        this.vacancyList.clear();
    }

    public int getNumberOfStarts() {
        return numberOfStarts;
    }

    public List<Vacancy> getVacancyList() {
        return vacancyList;
    }

    public void setVacancyList(List<Vacancy> vacancyList) {
        this.vacancyList = vacancyList;
    }

    @Override
    public void close() throws Exception {
        this.conn.close();
    }
}
