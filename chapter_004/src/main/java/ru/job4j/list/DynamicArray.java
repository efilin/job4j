package ru.job4j.list;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DynamicArray<E> implements Iterable<E> {

    private Object[] container = new Object[10];
    private int size = 0;
    protected int modCount = 0;


    void checkCapacity() {
        if (size == container.length) {
            modCount++;
            container = Arrays.copyOf(container, container.length + container.length / 2);
        }
    }


    void add(E e) {
        checkCapacity();
        container[size++] = e;
    }


    E get(int index) {
        return (E) container[index];
    }


    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int index = 0;
            int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public E next() {
                checkForComodification();
                int i = index;
                if (i >= size) {
                    throw new NoSuchElementException();
                }
                Object[] elementData = DynamicArray.this.container;
                if (i >= elementData.length) {
                    throw new ConcurrentModificationException();
                }
                index = i + 1;
                return (E) elementData[i];
            }

            final void checkForComodification() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
            }
        };

    }
}
