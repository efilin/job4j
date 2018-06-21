package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class LinkedListContainerTest {

    private LinkedListContainer<Integer> listContainer;

    @Before
    public void setUp() {
        listContainer = new LinkedListContainer<>();
        listContainer.add(4);
        listContainer.add(7);
        listContainer.add(9);

    }

    @Test
    public void whenAddThenGet() {
        assertThat(listContainer.get(2), is(9));
    }

    @Test
    public void whenAddThenDelete() {
        listContainer.delete();
        assertThat(listContainer.get(1), is(9));
    }


    @Test
    public void iterator() {
    }
}