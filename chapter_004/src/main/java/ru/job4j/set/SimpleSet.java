package ru.job4j.set;

import ru.job4j.list.DynamicArray;

import java.util.Iterator;

public class SimpleSet<E> implements Iterable<E> {
    DynamicArray<E> simpleList = new DynamicArray<>();

    public void add(E e) {
        if (!contains(e)) {
            simpleList.add(e);
        }
    }

    public boolean contains(E e) {
        for (E element : simpleList) {
            if (element.equals(e)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return simpleList.iterator();
    }
}
