package ru.job4j.magnit;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import java.io.File;
import java.util.Arrays;
import java.util.List;


public class StoreXML {
    File target;
    List<Field> entry;


    public StoreXML(File target) {
        this.target = target;
    }

    public void save(List<Field> entry) throws JAXBException {

        JAXBContext jaxbContext = JAXBContext.newInstance(Entry.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.marshal(
                new Entry(Arrays.asList(new Field(1), new Field(2))),
                System.out);

    }

    public List<Field> makeListFromDb() {

        return null;
    }



    @XmlRootElement
    public static class Entry {
        private List<Field> values;

        public Entry() {
        }

        public Entry(List<Field> values) {
            this.values = values;
        }

        public List<Field> getValues() {
            return values;
        }

        public void setValues(List<Field> values) {
            this.values = values;
        }
    }

    @XmlRootElement
    public static class Field {
        private int value;

        public Field() {
        }

        public Field(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }



}
