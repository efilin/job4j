package ru.job4j.set;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleHashSetTest {
    private SimpleHashSet<Integer> simpleHashSet;

    @Before
    public void setUp() {
        simpleHashSet = new SimpleHashSet<>();
    }

    @Test
    public void whenAddThenCheckContains() {
        assertThat(simpleHashSet.contains(5), is(false));
        simpleHashSet.add(5);
        assertThat(simpleHashSet.contains(5), is(true));
    }

    @Test
    public void whenAddThenRemoveThenCheck() {
        simpleHashSet.add(7);
        simpleHashSet.add(9);
        assertThat(simpleHashSet.contains(9), is(true));
        simpleHashSet.remove(9);
        assertThat(simpleHashSet.contains(9), is(false));

    }

}