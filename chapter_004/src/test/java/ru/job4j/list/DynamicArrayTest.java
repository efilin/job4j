package ru.job4j.list;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class DynamicArrayTest {

    @Test
    public void whenAddThenGet() {
        DynamicArray<Integer> dynArray = new DynamicArray<>();
        dynArray.add(4);
        dynArray.add(5);
        assertThat(dynArray.get(1), is(5));
    }

    @Test
    public void whenMoreThanTenElementsAndArrayGrows() {
        DynamicArray<Integer> dynArray = new DynamicArray<>();
        dynArray.add(3);
        dynArray.add(4);
        dynArray.add(5);
        dynArray.add(6);
        dynArray.add(7);
        dynArray.add(8);
        dynArray.add(9);
        dynArray.add(10);
        dynArray.add(11);
        dynArray.add(12);
        dynArray.add(13);
        assertThat(dynArray.get(10), is(13));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenIterateTillEndOfElements() {
        DynamicArray<Integer> dynArray = new DynamicArray<>();
        dynArray.add(3);
        dynArray.add(4);
        Iterator<Integer> it = dynArray.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(4));
        assertThat(it.hasNext(), is(false));
        it.next();
    }

    @Test
    public void whenIterateThenRemove() {
        DynamicArray<Integer> dynArray = new DynamicArray<>();
        dynArray.add(7);
        dynArray.add(9);
        dynArray.add(11);
        assertThat(dynArray.get(1), is(9));
        Iterator<Integer> it = dynArray.iterator();
        it.next();
        it.next();
        it.remove();
        assertThat(dynArray.get(1), is(11));
    }

    @Test(expected = IllegalStateException.class)
    public void whenRemoveWithoutIterate() {
        DynamicArray<Integer> dynArray = new DynamicArray<>();
        dynArray.add(2);
        dynArray.add(4);
        dynArray.add(6);
        Iterator<Integer> it = dynArray.iterator();
        it.remove();
    }
}