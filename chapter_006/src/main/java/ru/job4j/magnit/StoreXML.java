package ru.job4j.magnit;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class StoreXML implements AutoCloseable {
    private File target;
    private List<Field> entries;
    private Config config;
    private Connection conn;

    public StoreXML(Config config, File target) {
        this.config = config;
        try {
            conn = DriverManager.getConnection(this.config.getUrl());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.target = target;
    }

    public void save(List<Field> list) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Entries.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.marshal(new Entries(list), target);

    }

    public void makeListFromDb() {

        List<Field> result = new ArrayList<>();
        try (Statement stat = this.conn.createStatement()) {
            ResultSet rs = stat.executeQuery("SELECT * FROM entry");
            while (rs.next()) {
                result.add(new Field(rs.getInt("field")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        entries = result;
    }

    @Override
    public void close() throws SQLException {
        this.conn.close();
    }


    @XmlRootElement
    public static class Entries {
        public List<Field> entry;

        public Entries() {
        }

        public Entries(List<Field> entry) {
            this.entry = entry;
        }
    }

    @XmlRootElement
    public static class Field {
        public int field;

        public Field() {
        }

        public Field(int field) {
            this.field = field;
        }
    }


    public List<Field> getEntries() {
        return entries;
    }
}
