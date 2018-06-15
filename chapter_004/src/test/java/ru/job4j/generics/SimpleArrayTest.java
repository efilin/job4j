package ru.job4j.generics;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleArrayTest {

    @Test
    public void whenAddItem() {
        SimpleArray<Integer> simpleArray = new SimpleArray<Integer>(10);
        simpleArray.add(4);
        simpleArray.add(7);
        int result = simpleArray.get(1);
        assertThat(result, is(7));
    }

    @Test
    public void whenAddThenSet() {
        SimpleArray<Integer> simpleArray = new SimpleArray<Integer>(10);
        simpleArray.add(4);
        simpleArray.add(7);
        simpleArray.set(1, 10);
        int result = simpleArray.get(1);
        assertThat(result, is(10));
    }

    @Test
    public void whenDeleteSecondElementAndThirdBecomeSecond() {
        SimpleArray<Integer> simpleArray = new SimpleArray<Integer>(10);
        simpleArray.add(4);
        simpleArray.add(7);
        simpleArray.add(9);
        simpleArray.delete(1);
        int result = simpleArray.get(1);
        assertThat(result, is(9));
    }
}