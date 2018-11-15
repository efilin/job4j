package ru.job4j.tracker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class TrackerSQL implements ITracker, AutoCloseable {

    private Connection connection;

    private static final Logger LOGGER = LoggerFactory.getLogger(TrackerSQL.class);

    public TrackerSQL() {
        init();
        try {
            Statement stat = connection.createStatement();
            stat.executeUpdate("CREATE TABLE IF NOT EXISTS item(id SERIAL PRIMARY KEY, name VARCHAR(30), description VARCHAR(30),created BIGINT);");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public boolean init() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            this.connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return this.connection != null;
    }

    @Override
    public Item add(Item item) {
        try (TrackerSQL sql = new TrackerSQL()) {
            PreparedStatement pStat = connection.prepareStatement("INSERT INTO item(name, description, created) values (?,?,?);");
            pStat.setString(1, item.getName());
            pStat.setString(2, item.getDescription());
            pStat.setLong(3, item.getCreate());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public void replace(int id, Item item) {
        try (TrackerSQL sql = new TrackerSQL()) {
            PreparedStatement pStat = connection.prepareStatement("UPDATE item SET name = ?, description = ?, created = ? WHERE id = ?");
            pStat.setString(1, item.getName());
            pStat.setString(2, item.getDescription());
            pStat.setLong(3, item.getCreate());
            pStat.setInt(4, id);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(int id) {
        try (TrackerSQL sql = new TrackerSQL()) {
            PreparedStatement pStat = connection.prepareStatement("DELETE FROM item WHERE id = ?");
            pStat.setInt(1, id);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Item> findAll() {
        List<Item> result = new ArrayList<>();
        try (TrackerSQL sql = new TrackerSQL()) {
            Statement stat = connection.createStatement();
            ResultSet rs = stat.executeQuery("SELECT * FROM TABLE item");
            while (rs.next()) {
                Item item = new Item(rs.getString("name"), rs.getString("description"), rs.getLong("created"));
                result.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public List<Item> findByName(String name) {
        return null;
    }

    @Override
    public Item findById(int id) {
        return null;
    }

    @Override
    public void close() throws Exception {
        this.connection.close();
    }


}
