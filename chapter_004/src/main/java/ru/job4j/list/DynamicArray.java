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


    public void add(E e) {
        checkCapacity();
        container[size++] = e;
    }


    E get(int index) {
        return (E) container[index];
    }

    public E remove(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        }
        modCount++;
        E oldValue = (E) container[index];

        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(container, index + 1, container, index,
                    numMoved);
        }
        container[--size] = null;

        return oldValue;
    }


    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int index = 0;
            int lastRet = -1;
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
                lastRet = i;
                return (E) elementData[lastRet];
            }

            public void remove() {
                if (lastRet < 0) {
                    throw new IllegalStateException();
                }
                checkForComodification();

                try {
                    DynamicArray.this.remove(lastRet);
                    index = lastRet;
                    lastRet = -1;
                    expectedModCount = modCount;
                } catch (IndexOutOfBoundsException ex) {
                    throw new ConcurrentModificationException();
                }
            }

            final void checkForComodification() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
            }
        };
    }
}
