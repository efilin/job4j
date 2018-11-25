package ru.job4j.magnit;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class StoreXML {
    File target;
    private List<Field> entries;

    private Connection conn;
    //private StoreSQL sql;

    public StoreXML(Connection conn) {
        this.conn = conn;
    }

    /*public StoreXML(File target) {
        this.target = target;
    }*/
    public void save(List<Field> list) throws JAXBException {
        File file = new File("C:\\sqlite\\file.xml");
        JAXBContext jaxbContext = JAXBContext.newInstance(Entry.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.marshal(new Entry(list), file);

    }

    public void makeListFromDb() {

        List<Field> result = new ArrayList<>();
        try {

            Statement stat = this.conn.createStatement();
            ResultSet rs = stat.executeQuery("SELECT * FROM entry");
            while (rs.next()) {
                result.add(new Field(rs.getInt("field")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        entries = result;
    }


    @XmlRootElement
    public static class Entry {
        public List<Field> fields;

        public Entry() {
        }

        public Entry(List<Field> fields) {
            this.fields = fields;
        }
    }

    @XmlRootElement
    public static class Field {
        public int value;

        public Field() {
        }

        public Field(int value) {
            this.value = value;
        }
    }


    public List<Field> getEntries() {
        return entries;
    }
}
