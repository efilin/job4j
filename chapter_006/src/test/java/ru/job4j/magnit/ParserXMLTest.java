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
        System.setOut(new PrintStream(this.out));
    }

    @After
    public void backOutput() {
        System.setOut(this.stdout);
    }

    @Test
    public void whenNIsTenAndAnswerIsFortyFive() {
        StoreSQL sql = new StoreSQL(new Config(), 10);
        StoreXML xml = new StoreXML(new Config(), new File("file.xml"));
        xml.makeListFromDb();
        try {
            xml.save(xml.getEntries());
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        ConvertXSQT convertXSQT = new ConvertXSQT();
        try {
            convertXSQT.convert(new File("file.xml"),
                    new File("converted.xml"),
                    new File("scheme.xml"));
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        new ParserXML();
        assertThat(new String(out.toByteArray()), is("45"));
    }

    @Test
    public void whenNIsFiveAndAnswerIsTen() {
        StoreSQL sql = new StoreSQL(new Config(), 5);
        StoreXML xml = new StoreXML(new Config(), new File("file.xml"));
        xml.makeListFromDb();
        try {
            xml.save(xml.getEntries());
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        ConvertXSQT convertXSQT = new ConvertXSQT();
        try {
            convertXSQT.convert(new File("file.xml"),
                    new File("converted.xml"),
                    new File("scheme.xml"));
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        new ParserXML();
        assertThat(new String(out.toByteArray()), is("10"));
    }

}