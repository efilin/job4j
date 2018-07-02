package ru.job4j.set;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleHashSetTest {
    private SimpleHashSet<Integer> simpleHashSet;

    @Before
    public void setUp() throws Exception {
        simpleHashSet = new SimpleHashSet<>();
        simpleHashSet.add(5);
        simpleHashSet.add(7);
        simpleHashSet.add(9);
    }

    @Test
    public void whenAddThenCheckContains() {
        //assertThat(simpleHashSet.contains(7), is(true));
        //assertThat(simpleHashSet.contains(11), is(false));
    }

    @Test
    public void contains() {
    }

    @Test
    public void remove() {
    }
}