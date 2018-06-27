package ru.job4j.set;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleLinkedSetTest {
    private SimpleLinkedSet<Integer> linkedSet;

    @Before
    public void setUp() {
        linkedSet = new SimpleLinkedSet<>();
        linkedSet.add(3);
        linkedSet.add(5);
        linkedSet.add(7);
    }

    @Test
    public void whenAddAndElementIsAdded() {
        assertThat(linkedSet.contains(8), is(false));
        linkedSet.add(8);
        assertThat(linkedSet.contains(8), is(true));
    }

    @Test
    public void whenAddSameElementAndItIsNotAdded() {
        int index1 = 0;
        int index2 = 0;
        for (Integer element : linkedSet) {
            index1++;
        }
        linkedSet.add(5);
        for (Integer element : linkedSet) {
            index2++;
        }
        assertThat(index1, is(index2));
    }
}