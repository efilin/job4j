package ru.job4j.set;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleSetTest {
    private SimpleSet<Integer> simpleSet;

    @Before
    public void setUp() {
        simpleSet = new SimpleSet<>();
        simpleSet.add(3);
        simpleSet.add(5);
        simpleSet.add(7);
    }

    @Test
    public void whenAddAndElementIsAdded() {
        simpleSet.add(9);
        assertThat(simpleSet.contains(9), is(true));
    }

    @Test
    public void whenAddSameElementAndItIsNotAdded() {
        int index1 = 0;
        int index2 = 0;
        for (Integer element : simpleSet) {
            index1++;
        }
        simpleSet.add(7);
        for (Integer element : simpleSet) {
            index2++;
        }
        assertThat(index1, is(index2));
    }
}