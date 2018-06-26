package ru.job4j.list;

import org.junit.Test;
import org.junit.Before;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleArrayListTest {

    private SimpleArrayList<Integer> list;


    private void setUp() {
        list = new SimpleArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
    }

    @Test
    public void whenAddThreeElementsThenUseGetOneResultTwo() {
        setUp();
        assertThat(list.get(1), is(2));
    }

    @Test
    public void whenAddThreeElementsThenUseGetSizeResultThree() {
        setUp();
        assertThat(list.getSize(), is(3));
    }

    @Test
    public void whenAddThenDeleteThenGetZeroElementResultIsTwo() {
        setUp();
        list.delete();
        assertThat(list.get(0), is(2));
        assertThat(list.size, is(2));
    }

    @Test
    public void whenHasCycleInTheEnd() {
        SimpleArrayList.Node<Integer> first = new SimpleArrayList.Node<>(1);
        SimpleArrayList.Node<Integer> second = new SimpleArrayList.Node<>(2);
        SimpleArrayList.Node<Integer> third = new SimpleArrayList.Node<>(3);
        SimpleArrayList.Node<Integer> fourth = new SimpleArrayList.Node<>(4);
        first.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = first;
        assertThat(SimpleArrayList.hasCycle(first), is(true));
    }

    @Test
    public void whenHasCycleInTheMiddle() {
        SimpleArrayList.Node<Integer> first = new SimpleArrayList.Node<>(1);
        SimpleArrayList.Node<Integer> second = new SimpleArrayList.Node<>(2);
        SimpleArrayList.Node<Integer> third = new SimpleArrayList.Node<>(3);
        SimpleArrayList.Node<Integer> fourth = new SimpleArrayList.Node<>(4);
        first.next = second;
        second.next = third;
        third.next = second;
        fourth.next = first;
        assertThat(SimpleArrayList.hasCycle(first), is(true));
    }

    @Test
    public void whenHasNotCycle() {
        SimpleArrayList.Node<Integer> first = new SimpleArrayList.Node<>(1);
        SimpleArrayList.Node<Integer> second = new SimpleArrayList.Node<>(2);
        SimpleArrayList.Node<Integer> third = new SimpleArrayList.Node<>(3);
        SimpleArrayList.Node<Integer> fourth = new SimpleArrayList.Node<>(4);
        first.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = null;
        assertThat(SimpleArrayList.hasCycle(first), is(false));

    }
}