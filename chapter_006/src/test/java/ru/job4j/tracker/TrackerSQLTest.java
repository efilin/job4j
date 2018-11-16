package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;


import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;


public class TrackerSQLTest {
    @Test
    public void checkConnection() {
        TrackerSQL sql = new TrackerSQL();
        assertThat(sql.init(), is(true));
    }

    @Test
    public void whenAddToServerAndFindByName() {
        TrackerSQL sql = new TrackerSQL();
        Item itemOne = new Item("test1", "testDescription", 123);
        sql.add(itemOne);
        Item itemTwo = new Item("test2", "testDescription2", 124);
        sql.add(itemTwo);
        Item result = sql.findByName("test1").get(0);
        assertEquals(itemOne, result);
    }

    @Test
    public void whenReplaceOnServerAndFindById() {
        TrackerSQL sql = new TrackerSQL();
        Item itemThree = new Item("test3", "testDescription3", 125);
        sql.replace(2, itemThree);
        Item result = sql.findById(2);
        assertEquals(itemThree, result);
    }

    @Test
    public void whenDeleteOnServer() {
        TrackerSQL sql = new TrackerSQL();
        sql.delete(2);
        assertThat(sql.findAll().size(), is(1));
    }

    @Before
    public void setUp() throws Exception {
        TrackerSQL sql = new TrackerSQL();
        Item itemOne = new Item("test1", "testDescription", 123);
        sql.add(itemOne);
        Item itemTwo = new Item("test2", "testDescription2", 124);
        sql.add(itemTwo);
    }

    @After
    public void tearDown() throws Exception {
        TrackerSQL sql = new TrackerSQL();
        sql.dropTable();
    }
}