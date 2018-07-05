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

    @Test
    public void whenAddAndHashSetResize() {
        simpleHashSet.add(5);
        assertThat(simpleHashSet.getArrayLength(), is(10));
        simpleHashSet.add(1);
        simpleHashSet.add(2);
        simpleHashSet.add(3);
        simpleHashSet.add(4);
        simpleHashSet.add(6);
        simpleHashSet.add(7);
        simpleHashSet.add(8);
        simpleHashSet.add(9);
        assertThat(simpleHashSet.getArrayLength(), is(20));
    }

}