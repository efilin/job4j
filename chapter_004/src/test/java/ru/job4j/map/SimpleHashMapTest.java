package ru.job4j.map;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleHashMapTest {
    private SimpleHashMap<Integer, String> simpleHashMap;

    @Before
    public void setUp() {
        simpleHashMap = new SimpleHashMap<>();
        simpleHashMap.insert(3, "first");
        simpleHashMap.insert(4, "second");
        simpleHashMap.insert(5, "third");
    }

    @Test
    public void whenInsertThenGetThisElement() {
        assertNull(simpleHashMap.get(7));
        simpleHashMap.insert(7, "fourth");
        assertThat(simpleHashMap.get(7), is("fourth"));
    }

    @Test
    public void whenInsertThenDeleteThisElement() {
        assertThat(simpleHashMap.get(4), is("second"));
        simpleHashMap.delete(4);
        assertNull(simpleHashMap.get(4));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenIterateThenGetException() {
        Iterator<Integer> it = simpleHashMap.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(4));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(5));
        assertThat(it.hasNext(), is(false));
        it.next();
    }
}