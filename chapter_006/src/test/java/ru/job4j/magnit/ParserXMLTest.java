package ru.job4j.magnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.xml.bind.JAXBException;
import javax.xml.transform.TransformerException;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ParserXMLTest {

    private final PrintStream stdout = System.out;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    @Before
    public void loadOutput() {
        System.out.println("execute before method");
        System.setOut(new PrintStream(this.out));
    }

    @After
    public void backOutput() {
        System.setOut(this.stdout);
        System.out.println("execute after method");
    }

    @Test
    public void whenNIsTenAndAnswerIsFortyFive() {
        StoreSQL sql = new StoreSQL(new Config(), 10);
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
        new ParserXML();
        assertThat(new String(out.toByteArray()), is("45"));
    }

    @Test
    public void whenNIsMillionAndTestTimeLessThanFiveMinutes() {
        StoreSQL sql = new StoreSQL(new Config(), 1000000);
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
        new ParserXML();
        assertThat(new String(out.toByteArray()), is("1783293664"));
    }
}