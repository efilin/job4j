package ru.job4j.set;

import ru.job4j.list.LinkedListContainer;

import java.util.Iterator;

public class SimpleLinkedSet<E> implements Iterable<E> {
    LinkedListContainer<E> listContainer = new LinkedListContainer<>();

    void add(E e) {
        if (!contains(e)) {
            listContainer.add(e);
        }
    }

    public boolean contains(E e) {
        for (E element : listContainer) {
            if (element.equals(e)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return listContainer.iterator();
    }
}
