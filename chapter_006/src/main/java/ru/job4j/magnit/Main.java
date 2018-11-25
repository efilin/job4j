package ru.job4j.magnit;

import javax.xml.bind.JAXBException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        StoreSQL sql = new StoreSQL(new Config());
        StoreXML xml = new StoreXML(sql.getConn());
        xml.makeListFromDb();
        try {
            xml.save(xml.getEntries());
        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }


}
