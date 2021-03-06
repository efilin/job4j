package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

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

    @Test(expected = NoSuchElementException.class)
    public void whenAddThenGet() {
        assertThat(listContainer.get(2), is(9));
        listContainer.getNode(20);
    }

    @Test
    public void whenAddThenDelete() {
        listContainer.delete();
        assertThat(listContainer.get(1), is(9));
    }


    @Test(expected = NoSuchElementException.class)
    public void whenIterateThenGetException() {
        Iterator<Integer> it = listContainer.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(4));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(7));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(9));
        assertThat(it.hasNext(), is(false));
        it.next();
    }

    @Test(expected = IllegalStateException.class)
    public void whenDeleteWithoutIterateAndGetException() {
        Iterator<Integer> it = listContainer.iterator();
        it.remove();
    }

    @Test
    public void whenIterateThenDeleteFirst() {
        Iterator<Integer> it = listContainer.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(listContainer.first.date, is(4));
        assertThat(it.next(), is(4));
        it.remove();
        assertThat(listContainer.first.date, is(7));
        assertThat(it.next(), is(7));
    }

    @Test
    public void whenIterateThenDeleteSecond() {
        Iterator<Integer> it = listContainer.iterator();
        it.next();
        it.next();
        it.remove();
        assertThat(listContainer.first.date, is(4));
        assertThat(it.next(), is(9));
    }

    @Test
    public void whenIterateThenDeleteLast() {
        Iterator<Integer> it = listContainer.iterator();
        assertThat(listContainer.last.date, is(9));
        it.next();
        it.next();
        it.next();
        it.remove();
        assertThat(listContainer.last.date, is(7));
    }
}