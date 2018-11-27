package ru.job4j.magnit;

import javax.xml.bind.JAXBException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        StoreSQL sql = new StoreSQL(new Config());
        StoreXML xml = new StoreXML(sql.getConn(), new File("C:\\sqlite\\file.xml"));
        xml.makeListFromDb();
        try {
            xml.save(xml.getEntries());
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        ConvertXSQT convertXSQT = new ConvertXSQT();
        try {
            convertXSQT.convert(new File("C:\\sqlite\\file.xml"),
                    new File("C:\\sqlite\\converted.xml"),
                    new File("C:\\sqlite\\scheme.xml"));
        } catch (TransformerException e) {
            e.printStackTrace();
        }

    }


}
